package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.dao.RoleDao;
import learning.more.dao.mapper.RoleMapper;
import learning.more.model.domain.Role;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【role(角色信息表)】的数据库操作Service实现
* @createDate 2025-05-11 13:01:49
*/
@Service
public class RoleDaoImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleDao {

}




