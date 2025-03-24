package learning.more.service.weeklyplan.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import learning.more.common.AppResult;
import learning.more.common.enums.ResultCode;
import learning.more.dao.ClassDao;
import learning.more.dao.SchoolDao;
import learning.more.dao.WeeklyPlanDao;
import learning.more.exception.ApplicationException;
import learning.more.model.domain.Class;
import learning.more.model.domain.School;
import learning.more.model.domain.WeeklyPlan;
import learning.more.model.dto.WeeklyPlanCreateDTO;
import learning.more.model.dto.WeeklyPlanUpdateDTO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SuccessVO;
import learning.more.model.vo.WeeklyPlanOverviewVO;
import learning.more.service.weeklyplan.IWeeklyPlanService;
import learning.more.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeeklyPlanServiceImpl implements IWeeklyPlanService {
    @Resource
    private WeeklyPlanDao weeklyPlanDao;

    @Resource
    private ClassDao classDao;

    @Resource
    private SchoolDao schoolDao;

    @Override
    public PageItem<List<WeeklyPlanOverviewVO>> listWeeklyPlanPage(Integer pageNum, Integer pageSize, Long classId, Long schoolId,String searchString) {
        if (pageNum == null || pageSize == null || pageSize <= 0 || pageNum <= 0) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Page<WeeklyPlan> pageObj = new Page<>(pageNum, pageSize);
        Boolean schoolIdIsOk = (schoolId != null && schoolId >= 0);
        Page<WeeklyPlan> planPage = weeklyPlanDao.getBaseMapper().selectPage(pageObj,
                new LambdaQueryWrapper<WeeklyPlan>()
                        .select(WeeklyPlan::getId, WeeklyPlan::getName,
                                WeeklyPlan::getAuthor, WeeklyPlan::getUpdatedTime,
                                WeeklyPlan::getClassId, WeeklyPlan::getSchoolId)
                        .like(!StringUtil.isNullAndEmpty(searchString), WeeklyPlan::getName, searchString)
                        .eq(schoolIdIsOk, WeeklyPlan::getSchoolId, schoolId)
                        .eq(schoolIdIsOk && (classId != null && classId >= 0), WeeklyPlan::getClassId, classId)
                        .eq(WeeklyPlan::getIsDeleted, 0));
        if (planPage.getRecords().isEmpty()) {
            return new PageItem<>(0L, 0L, pageNum, Collections.EMPTY_LIST);
        }
        Map<Long, String> classIdWithNameMap = classDao.lambdaQuery()
                .select(Class::getName, Class::getId)
                .eq(Class::getIsDeleted, 0)
                .in(Class::getId, planPage.getRecords().stream().map(WeeklyPlan::getClassId).toList())
                .list().stream().collect(Collectors.toMap(Class::getId, Class::getName));
        Map<Long, String> schoolIdWithNameMap = schoolDao.lambdaQuery()
                .select(School::getName, School::getId)
                .eq(School::getIsDeleted, 0)
                .in(School::getId, planPage.getRecords().stream().map(WeeklyPlan::getSchoolId).toList())
                .list().stream().collect(Collectors.toMap(School::getId, School::getName));
        List<WeeklyPlanOverviewVO> weeklyPlanOverviewVOS = planPage.getRecords()
                .stream()
                .map(plan -> new WeeklyPlanOverviewVO(plan.getId(),
                        plan.getName().toString(), schoolIdWithNameMap.get(plan.getSchoolId()),
                        classIdWithNameMap.get(plan.getClassId()), plan.getAuthor(), plan.getUpdatedTime()))
                .toList();

        return new PageItem<>(planPage.getTotal(), planPage.getPages(), pageNum, weeklyPlanOverviewVOS);
    }

    @Override
    public SuccessVO createWeeklyPlan(WeeklyPlanCreateDTO weeklyPlanCreateDTO) {
        if (weeklyPlanCreateDTO == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Date now = new Date();
        WeeklyPlan weeklyPlan = WeeklyPlan.builder()
                .name(weeklyPlanCreateDTO.getName())
                .schoolId(weeklyPlanCreateDTO.getSchoolId())
                .classId(weeklyPlanCreateDTO.getClassId())
                .author(weeklyPlanCreateDTO.getAuthor())
                .createdTime(now)
                .updatedTime(now)
                .isDeleted(0)
                .build();

        boolean saved = weeklyPlanDao.save(weeklyPlan);
        if (!saved) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }

    @Override
    public SuccessVO updateWeeklyPlan(WeeklyPlanUpdateDTO weeklyPlanUpdateDTO) {
        if (weeklyPlanUpdateDTO == null || weeklyPlanUpdateDTO.getId() == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }

        WeeklyPlan weeklyPlan = WeeklyPlan.builder()
                .id(weeklyPlanUpdateDTO.getId())
                .name(weeklyPlanUpdateDTO.getName())
                .schoolId(weeklyPlanUpdateDTO.getSchoolId())
                .classId(weeklyPlanUpdateDTO.getClassId())
                .author(weeklyPlanUpdateDTO.getAuthor())
                .updatedTime(new Date())
                .build();

        boolean updated = weeklyPlanDao.updateById(weeklyPlan);
        if (!updated) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }

    @Override
    public SuccessVO deleteWeeklyPlan(Long planId) {
        if (planId == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        boolean updated = weeklyPlanDao.lambdaUpdate()
                .eq(WeeklyPlan::getId, planId)
                .set(WeeklyPlan::getIsDeleted, 1)
                .update();
        if (!updated) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }
}
