package learning.more.dao.mapper;

import learning.more.model.domain.WeeklyPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learning.more.model.vo.WeeklyPlanItemDetail;

import java.util.List;

/**
* @author ASUS
* @description 针对表【weekly_plan】的数据库操作Mapper
* @createDate 2024-11-29 12:11:28
* @Entity generator.domain.WeeklyPlan
*/
public interface WeeklyPlanMapper extends BaseMapper<WeeklyPlan> {

    List<WeeklyPlanItemDetail> getWeeklyPlanDetail(Long planId);
}




