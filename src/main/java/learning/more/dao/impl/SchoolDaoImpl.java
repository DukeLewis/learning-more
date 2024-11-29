package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.model.domain.School;
import learning.more.dao.mapper.SchoolMapper;
import learning.more.dao.SchoolDao;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【school】的数据库操作Service实现
* @createDate 2024-11-29 12:11:28
*/
@Service
public class SchoolDaoImpl extends ServiceImpl<SchoolMapper, School>
    implements SchoolDao {

}




