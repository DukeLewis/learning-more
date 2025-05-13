package learning.more.dao.mapper;

import learning.more.model.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【role(角色信息表)】的数据库操作Mapper
* @createDate 2025-05-11 13:01:49
* @Entity generator.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




