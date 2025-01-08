package learning.more.dao.mapper;

import learning.more.model.domain.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learning.more.model.vo.ClassInfoVO;
import org.apache.ibatis.annotations.Param;

/**
* @author ASUS
* @description 针对表【class】的数据库操作Mapper
* @createDate 2024-11-29 12:11:28
* @Entity generator.domain.Class
*/
public interface ClassMapper extends BaseMapper<Class> {
    /**
     * 获取班级详细信息
     * @param id 班级 id
     * @return 班级详细信息
     */
    ClassInfoVO getClassInfo(@Param("id") Long id);
}




