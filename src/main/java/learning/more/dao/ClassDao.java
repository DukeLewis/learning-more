package learning.more.dao;

import learning.more.model.domain.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import learning.more.model.vo.ClassInfoVO;
import learning.more.model.vo.ClassOverviewVO;

import java.util.List;

/**
* @author ASUS
* @description 针对表【class】的数据库操作Service
* @createDate 2024-11-29 12:11:28
*/
public interface ClassDao extends IService<Class> {

    /**
     * 获取班级概览
     * @return 班级概览信息列表
     */
    List<ClassOverviewVO> listClassOverview();

    /**
     * 获取班级详细信息
     * @param id 班级 id
     * @return 班级详细信息
     */
    ClassInfoVO getClassInfo(Long id);
}
