package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.dao.UserDao;
import learning.more.model.domain.User;
import learning.more.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-11-29 13:33:38
*/
@Service
public class UserDaoImpl extends ServiceImpl<UserMapper, User>
    implements UserDao {

}




