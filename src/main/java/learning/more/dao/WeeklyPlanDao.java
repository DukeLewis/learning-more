package learning.more.dao;

import learning.more.model.domain.WeeklyPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import learning.more.model.vo.WeeklyPlanItemDetail;

import java.util.List;

/**
* @author ASUS
* @description 针对表【weekly_plan】的数据库操作Service
* @createDate 2024-11-29 12:11:28
*/
public interface WeeklyPlanDao extends IService<WeeklyPlan> {
    /**
     * 获取周计划详情
     * @param planId 周计划 id
     * @return 周计划详情
     */
    List<WeeklyPlanItemDetail> getWeeklyPlanDetail(Long planId);
}
