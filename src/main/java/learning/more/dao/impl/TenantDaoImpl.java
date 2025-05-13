package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.dao.TenantDao;
import learning.more.model.domain.Tenant;
import learning.more.dao.mapper.TenantMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【tenant】的数据库操作Service实现
* @createDate 2025-05-12 12:11:19
*/
@Service
public class TenantDaoImpl extends ServiceImpl<TenantMapper, Tenant>
    implements TenantDao {

}




