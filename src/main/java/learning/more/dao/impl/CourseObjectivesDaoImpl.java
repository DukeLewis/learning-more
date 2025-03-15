package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import learning.more.dao.CourseObjectivesDao;
import learning.more.dao.mapper.CourseObjectivesMapper;
import learning.more.model.domain.CourseObjectives;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【course_objectives】的数据库操作Service实现
* @createDate 2025-02-05 18:57:05
*/
@Service
public class CourseObjectivesDaoImpl extends ServiceImpl<CourseObjectivesMapper, CourseObjectives>
    implements CourseObjectivesDao {

}




