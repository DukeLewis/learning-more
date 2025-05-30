package learning.more.service.course;

import io.swagger.v3.oas.annotations.Parameter;
import learning.more.model.domain.Course;
import learning.more.model.domain.CourseActivities;
import learning.more.model.domain.CourseObjectives;
import learning.more.model.dto.CourseCreateDTO;
import learning.more.model.dto.CourseUpdateDTO;
import learning.more.model.dto.GenerateCourseDTO;
import learning.more.model.vo.CourseDetailVO;
import learning.more.model.vo.CourseOverviewVO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SuccessVO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/5
 * @Copyright： https://github.com/DukeLewis
 */
public interface ICourseService {
    /**
     * 获取课程概览列表
     * @param page 页码
     * @param pageSize 每页数量
     * @param course 课程查询对象
     * @return 查询结果
     */
    PageItem<List<CourseOverviewVO>> listCourseOverviewPage(Integer page, Integer pageSize, Course course);

    /**
     * 获取课程详情
     * @param courseId 课程 id
     * @return 课程详情对象
     */
    CourseDetailVO getCourseDetail(Integer courseId);

    /**
     * 删除课程
     * @param courseId 课程 id
     * @return 操作成功响应
     */
    SuccessVO deleteCourse(Integer courseId);

    /**
     * 更新课程
     * @param courseUpdateDTO 更新课程信息
     * @return 操作成功响应
     */
    SuccessVO updateCourse(CourseUpdateDTO courseUpdateDTO);

    /**
     * 创建课程
     * @param courseCreateDTO 创建课程信息
     * @return 操作成功响应
     */
    SuccessVO<Long> createCourse(CourseCreateDTO courseCreateDTO);

    /**
     * 生成课程基本信息
     * @param generateCourseDTO 生成课程所需信息
     * @return SSE 响应
     */
    SseEmitter generateCourseBaseInfo(GenerateCourseDTO generateCourseDTO);

    /**
     * 创建课程（第一阶段）
     * @param courseCreateDTO 创建课程信息
     * @return 操作成功响应
     */
    SuccessVO<Long> createCourseFirst(CourseCreateDTO courseCreateDTO);

    /**
     * 更新课程（第一阶段）
     * @param courseUpdateDTO 课程更新信息
     * @return 操作成功响应
     */
    SuccessVO<Long> updateCourseFirst(CourseUpdateDTO courseUpdateDTO);

    /**
     * 生成课程目标
     * @param courseId 课程id
     * @return SSE 响应
     */
    SseEmitter generateCourseObjectives(Long courseId);

    /**
     * 更新课程（第二阶段）
     * @param courseObjectivesList 课程目标信息
     * @return 操作成功响应
     */
    SuccessVO<List<CourseObjectives>> createOrUpdateCourseSecond(List<CourseObjectives> courseObjectivesList);

    /**
     * 生成课程活动
     * @param courseId 课程 id
     * @return 流式响应
     */
    SseEmitter generateCourseActivities(Long courseId);

    /**
     * 更新课程（第三阶段）
     * @param courseActivitiesList 课程活动信息
     * @return 操作成功响应
     */
    SuccessVO<List<CourseActivities>> createOrUpdateCourseThird(List<CourseActivities> courseActivitiesList);
}
