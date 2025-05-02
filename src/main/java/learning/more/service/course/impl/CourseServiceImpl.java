package learning.more.service.course.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import learning.more.common.AppResult;
import learning.more.common.enums.PromptType;
import learning.more.common.enums.ResultCode;
import learning.more.dao.CourseActivitiesDao;
import learning.more.dao.CourseDao;
import learning.more.dao.CourseObjectivesDao;
import learning.more.dao.PromptDao;
import learning.more.exception.ApplicationException;
import learning.more.model.domain.Course;
import learning.more.model.domain.CourseActivities;
import learning.more.model.domain.CourseObjectives;
import learning.more.model.domain.Prompt;
import learning.more.model.dto.CourseCreateDTO;
import learning.more.model.dto.CourseUpdateDTO;
import learning.more.model.dto.GenerateCourseDTO;
import learning.more.model.vo.CourseDetailVO;
import learning.more.model.vo.CourseOverviewVO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SuccessVO;
import learning.more.service.ai.api.AiService;
import learning.more.service.course.ICourseService;
import learning.more.util.StringUtil;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/5
 * @Copyright： https://github.com/DukeLewis
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Resource
    private CourseDao courseDao;

    @Resource
    private CourseActivitiesDao courseActivitiesDao;

    @Resource
    private CourseObjectivesDao courseObjectivesDao;

    @Resource
    private AiService aiService;

    @Resource
    private PromptDao promptDao;

    @Override
    public PageItem<List<CourseOverviewVO>> listCourseOverviewPage(Integer page, Integer pageSize, Course course) {
        if (page == null || pageSize == null || pageSize <= 0 || page <= 0) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Page<Course> pageObj = new Page<>(page, pageSize);
        Page<Course> coursePage = courseDao.getBaseMapper().selectPage(pageObj,
                new LambdaQueryWrapper<Course>().select(Course::getId, Course::getName, Course::getDuration,
                        Course::getDescription, Course::getUpdatedTime)
                        .like(!StringUtil.isNullAndEmpty(course.getName()), Course::getName, course.getName())
                        .like(!StringUtil.isNullAndEmpty(course.getType()), Course::getType, course.getType())
                        .eq(course.getDuration() != null && course.getDuration() > 0, Course::getDuration, course.getDuration())
                        .eq(Course::getTenantId, course.getTenantId())
                        .eq(Course::getIsDeleted, 0));
        List<CourseOverviewVO> courseOverviewVOS = CourseOverviewVO.coursePage2VO(coursePage);
        return new PageItem<>(coursePage.getTotal(), coursePage.getPages(), page, courseOverviewVOS);
    }

    @Override
    public CourseDetailVO getCourseDetail(Integer courseId) {
        if (courseId == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Course course = courseDao.lambdaQuery().eq(Course::getId, courseId)
                .eq(Course::getIsDeleted, 0).one();
        List<CourseActivities> courseActivitiesList = courseActivitiesDao.lambdaQuery().eq(CourseActivities::getCourseId, courseId).list();
        List<CourseObjectives> courseObjectivesList = courseObjectivesDao.lambdaQuery().eq(CourseObjectives::getCourseId, courseId).list();
        return new CourseDetailVO(course.getId(), course.getName(), course.getDuration(),
                course.getDescription(), course.getAgeGroup(), course.getMaxStudents(),
                course.getTotalSessions(), course.getStartDate(), course.getEndDate(),
                course.getUpdatedTime(), course.getActiveStep(), course.getType(), courseObjectivesList,
                courseActivitiesList);
    }

    @Override
    public SuccessVO deleteCourse(Integer courseId) {
        if (courseId == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Course course = courseDao.lambdaQuery().eq(Course::getId, courseId).one();
        if (course == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        boolean updated = courseDao.lambdaUpdate().eq(Course::getId, courseId).set(Course::getIsDeleted, 1).update();
        boolean removedObjectives = courseObjectivesDao.remove(new LambdaQueryWrapper<CourseObjectives>().eq(CourseObjectives::getCourseId, courseId));
        boolean removedActivities = courseActivitiesDao.remove(new LambdaQueryWrapper<CourseActivities>().eq(CourseActivities::getCourseId, courseId));
//        if (!updated || !removedObjectives || !removedActivities) {
//            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
//        }
        return SuccessVO.successNotData();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SuccessVO updateCourse(CourseUpdateDTO courseUpdateDTO) {
        if (courseUpdateDTO == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        boolean updatedCourse = courseDao.lambdaUpdate().eq(Course::getId, courseUpdateDTO.getId())
                .set(Course::getName, courseUpdateDTO.getCourseName())
                .set(Course::getDuration, courseUpdateDTO.getDuration())
                .set(Course::getDescription, courseUpdateDTO.getCourseDescription())
                .set(Course::getAgeGroup, courseUpdateDTO.getAgeGroup())
                .set(Course::getMaxStudents, courseUpdateDTO.getMaxStudents())
                .set(Course::getTotalSessions, courseUpdateDTO.getTotalSessions())
                .set(Course::getStartDate, courseUpdateDTO.getStartTime())
                .set(Course::getEndDate, courseUpdateDTO.getEndTime())
                .set(courseUpdateDTO.getActiveStep() != null && courseUpdateDTO.getActiveStep() >= 0, Course::getActiveStep, courseUpdateDTO.getActiveStep())
                .set(Course::getUpdatedTime, new Date())
                .update();
        boolean updatedActivities = true;
        List<CourseActivities> activities = courseUpdateDTO.getActivities();
        if (activities != null && !activities.isEmpty()) {
            updatedActivities = courseActivitiesDao.updateBatchById(activities);
        }
        boolean updatedObjectives = true;
        List<CourseObjectives> objectives = courseUpdateDTO.getObjectives();
        if (objectives != null && !objectives.isEmpty()) {
            updatedObjectives = courseObjectivesDao.updateBatchById(objectives);
        }
//        if (!updatedCourse || !updatedActivities || !updatedObjectives) {
//            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
//        }
        return SuccessVO.successNotData();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SuccessVO<Long> createCourse(CourseCreateDTO courseCreateDTO) {
        if (courseCreateDTO == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Course course = Course.builder()
                .name(courseCreateDTO.getCourseName())
                .duration(courseCreateDTO.getDuration())
                .description(courseCreateDTO.getCourseDescription())
                .ageGroup(courseCreateDTO.getAgeGroup())
                .maxStudents(courseCreateDTO.getMaxStudents())
                .totalSessions(courseCreateDTO.getTotalSessions())
                .startDate(courseCreateDTO.getStartTime())
                .endDate(courseCreateDTO.getEndTime())
                .activeStep(0)
                .type(courseCreateDTO.getType())
                .updatedTime(new Date())
                .createdTime(new Date())
                .isDeleted(0)
                .build();
        boolean saveCourse = courseDao.save(course);
        boolean savedActivities = true;
        List<CourseActivities> activities = courseCreateDTO.getActivities();
        if (activities != null && !activities.isEmpty()) {
            savedActivities = courseActivitiesDao.saveBatch(activities);
        }
        boolean savedObjectives = true;
        List<CourseObjectives> objectives = courseCreateDTO.getObjectives();
        if (objectives != null && !objectives.isEmpty()) {
            savedObjectives = courseObjectivesDao.saveBatch(objectives);
        }
//        if (!saveCourse || !savedActivities || !savedObjectives) {
//            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
//        }
        return SuccessVO.successWithData(course.getId());
    }

    @Override
    public SseEmitter generateCourseBaseInfo(GenerateCourseDTO generateCourseDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("ageGroup", generateCourseDTO.getAgeGroup());
        map.put("maxStudents", generateCourseDTO.getMaxStudents());
        map.put("type", generateCourseDTO.getType());
        Prompt prompt = promptDao.lambdaQuery().eq(Prompt::getPromptType, PromptType.COURSE_BASE_INFO.toString())
                .eq(Prompt::getActive, 0).one();
        String promptRes = StringUtil.parsePromptString(prompt.getPrompt(), map);
        SseEmitter sseEmitter = new SseEmitter();
        aiService.createStreamChatCompletionAll(promptRes, sseEmitter);
        return sseEmitter;
    }

    @Override
    public SuccessVO<Long> createCourseFirst(CourseCreateDTO courseCreateDTO) {
        Course course = Course.builder()
                .name(courseCreateDTO.getCourseName())
                .duration(courseCreateDTO.getDuration())
                .description(courseCreateDTO.getCourseDescription())
                .ageGroup(courseCreateDTO.getAgeGroup())
                .maxStudents(courseCreateDTO.getMaxStudents())
                .totalSessions(courseCreateDTO.getTotalSessions())
                .startDate(courseCreateDTO.getStartTime())
                .endDate(courseCreateDTO.getEndTime())
                .activeStep(0)
                .type(courseCreateDTO.getType())
                .updatedTime(new Date())
                .createdTime(new Date())
                .isDeleted(0)
                .build();
        courseDao.save(course);
        return SuccessVO.successWithData(course.getId());
    }

    @Override
    public SuccessVO<Long> updateCourseFirst(CourseUpdateDTO courseUpdateDTO) {
        if (courseUpdateDTO.getId() == null || courseUpdateDTO.getId() <= 0) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Course course = Course.builder()
                .name(courseUpdateDTO.getCourseName())
                .duration(courseUpdateDTO.getDuration())
                .description(courseUpdateDTO.getCourseDescription())
                .ageGroup(courseUpdateDTO.getAgeGroup())
                .maxStudents(courseUpdateDTO.getMaxStudents())
                .totalSessions(courseUpdateDTO.getTotalSessions())
                .startDate(courseUpdateDTO.getStartTime())
                .endDate(courseUpdateDTO.getEndTime())
                .type(courseUpdateDTO.getType())
                .updatedTime(new Date())
                .build();
        courseDao.updateById(course);
        return SuccessVO.successWithData(courseUpdateDTO.getId());
    }

    @Override
    public SseEmitter generateCourseObjectives(Long courseId) {
        Map<String, Object> map = new HashMap<>();
        Course course = courseDao.getById(courseId);
        course.toMap(map);
        Prompt prompt = promptDao.lambdaQuery().eq(Prompt::getPromptType, PromptType.COURSE_OBJECTIVES.toString())
                .eq(Prompt::getActive, 0).one();
        String promptRes = StringUtil.parsePromptString(prompt.getPrompt(), map);
        SseEmitter sseEmitter = new SseEmitter();
        aiService.createStreamChatCompletionAll(promptRes, sseEmitter);
        return sseEmitter;
    }

    @Override
    public SuccessVO<List<CourseObjectives>> createOrUpdateCourseSecond(List<CourseObjectives> courseObjectivesList) {
        List<CourseObjectives> saveList = courseObjectivesList.stream().filter(courseObjectives -> courseObjectives.getId() == null).toList();
        List<CourseObjectives> updateList = courseObjectivesList.stream().filter(courseObjectives -> courseObjectives.getId() != null).toList();
        courseObjectivesDao.saveBatch(saveList);
        courseObjectivesDao.updateBatchById(updateList);
        return SuccessVO.successWithData(courseObjectivesList);
    }

    @Override
    public SseEmitter generateCourseActivities(Long courseId) {
        Map<String, Object> map = new HashMap<>();
        Course course = courseDao.getById(courseId);
        course.toMap(map);
        List<CourseObjectives> courseObjectivesList = courseObjectivesDao.lambdaQuery().eq(CourseObjectives::getCourseId, courseId)
                .list();
        Prompt prompt = promptDao.lambdaQuery().eq(Prompt::getPromptType, PromptType.COURSE_ACTIVITIES.toString())
                .eq(Prompt::getActive, 0).one();
        String promptRes = StringUtil.parsePromptString(prompt.getPrompt(), map);
        SseEmitter sseEmitter = new SseEmitter();
        aiService.createStreamChatCompletionAll(promptRes, sseEmitter);
        return sseEmitter;
    }
}
