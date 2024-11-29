package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.model.domain.WeeklyPlan;
import learning.more.dao.WeeklyPlanDao;
import learning.more.dao.mapper.WeeklyPlanMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【weekly_plan】的数据库操作Service实现
* @createDate 2024-11-29 12:11:28
*/
@Service
public class WeeklyPlanDaoImpl extends ServiceImpl<WeeklyPlanMapper, WeeklyPlan>
    implements WeeklyPlanDao {

}




