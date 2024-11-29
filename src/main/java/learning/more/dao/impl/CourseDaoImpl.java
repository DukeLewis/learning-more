package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.model.domain.Course;
import learning.more.dao.CourseDao;
import learning.more.dao.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【course】的数据库操作Service实现
* @createDate 2024-11-29 12:11:28
*/
@Service
public class CourseDaoImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseDao {

}




