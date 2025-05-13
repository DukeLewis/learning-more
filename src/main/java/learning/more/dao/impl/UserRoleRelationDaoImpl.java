package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.dao.UserRoleRelationDao;
import learning.more.dao.mapper.UserRoleRelationMapper;
import learning.more.model.domain.UserRoleRelation;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【user_role_relation(用户角色关系表)】的数据库操作Service实现
* @createDate 2025-05-11 13:01:49
*/
@Service
public class UserRoleRelationDaoImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation>
    implements UserRoleRelationDao {

}




