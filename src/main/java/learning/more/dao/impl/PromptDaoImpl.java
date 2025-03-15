package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.dao.PromptDao;
import learning.more.model.domain.Prompt;
import learning.more.dao.mapper.PromptMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【prompt】的数据库操作Service实现
* @createDate 2025-02-25 19:25:37
*/
@Service
public class PromptDaoImpl extends ServiceImpl<PromptMapper, Prompt>
    implements PromptDao {

}




