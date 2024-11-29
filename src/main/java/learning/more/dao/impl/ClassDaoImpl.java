package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.model.domain.Class;
import learning.more.dao.ClassDao;
import learning.more.dao.mapper.ClassMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【class】的数据库操作Service实现
* @createDate 2024-11-29 12:11:28
*/
@Service
public class ClassDaoImpl extends ServiceImpl<ClassMapper, Class>
    implements ClassDao {

}




