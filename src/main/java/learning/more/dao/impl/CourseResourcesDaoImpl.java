package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import learning.more.dao.CourseResourcesDao;
import learning.more.dao.mapper.CourseResourcesMapper;
import learning.more.model.domain.CourseResources;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【course_resources】的数据库操作Service实现
* @createDate 2025-02-05 18:57:05
*/
@Service
public class CourseResourcesDaoImpl extends ServiceImpl<CourseResourcesMapper, CourseResources>
    implements CourseResourcesDao {

}




