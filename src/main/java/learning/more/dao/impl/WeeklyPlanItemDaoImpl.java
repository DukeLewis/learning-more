package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.model.domain.WeeklyPlanItem;
import learning.more.dao.WeeklyPlanItemDao;
import learning.more.dao.mapper.WeeklyPlanItemMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【weekly_plan_item】的数据库操作Service实现
* @createDate 2024-11-29 12:11:29
*/
@Service
public class WeeklyPlanItemDaoImpl extends ServiceImpl<WeeklyPlanItemMapper, WeeklyPlanItem>
    implements WeeklyPlanItemDao {

}




