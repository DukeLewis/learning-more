package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import learning.more.dao.CourseActivitiesDao;
import learning.more.dao.mapper.CourseActivitiesMapper;
import learning.more.model.domain.CourseActivities;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【course_activities】的数据库操作Service实现
* @createDate 2025-02-05 18:57:05
*/
@Service
public class CourseActivitiesDaoImpl extends ServiceImpl<CourseActivitiesMapper, CourseActivities>
    implements CourseActivitiesDao {

}




