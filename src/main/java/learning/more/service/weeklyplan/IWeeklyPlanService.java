package learning.more.service.weeklyplan;

import learning.more.model.dto.WeeklyPlanCreateDTO;
import learning.more.model.dto.WeeklyPlanUpdateDTO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SuccessVO;
import learning.more.model.vo.WeeklyPlanOverviewVO;

import java.util.List;

public interface IWeeklyPlanService {
    /**
     * 获取周计划概览分页列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param classId 班级ID
     * @param schoolId 学校ID
     * @param searchString 搜索字符串
     * @return 分页数据结果
     */
    PageItem<List<WeeklyPlanOverviewVO>> listWeeklyPlanPage(Integer pageNum, Integer pageSize, Long classId, Long schoolId, String searchString);

    /**
     * 创建周计划
     * @param weeklyPlanCreateDTO 周计划创建信息
     * @return 操作成功响应
     */
    SuccessVO createWeeklyPlan(WeeklyPlanCreateDTO weeklyPlanCreateDTO);

    /**
     * 更新周计划
     * @param weeklyPlanUpdateDTO 周计划更新信息
     * @return 操作成功响应
     */
    SuccessVO updateWeeklyPlan(WeeklyPlanUpdateDTO weeklyPlanUpdateDTO);

    /**
     * 删除周计划
     * @param planId 周计划ID
     * @return 操作成功响应
     */
    SuccessVO deleteWeeklyPlan(Long planId);
}
