package learning.more.dao;

import learning.more.model.domain.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import learning.more.model.vo.ClassInfoVO;
import learning.more.model.vo.ClassOverviewVO;
import learning.more.model.vo.PageItem;

import java.util.List;

/**
* @author ASUS
* @description 针对表【class】的数据库操作Service
* @createDate 2024-11-29 12:11:28
*/
public interface ClassDao extends IService<Class> {

    /**
     * 获取班级概览
     * @param schoolId 学校 id
     * @return 班级概览信息列表
     */
    List<ClassOverviewVO> listClassOverview(Long schoolId);

    /**
     * 获取班级详细信息
     * @param id 班级 id
     * @return 班级详细信息
     */
    ClassInfoVO getClassInfo(Long id);

    /**
     * 获取班级概览分页
     * @param page 页码
     * @param limit 每页数量
     * @param clazz 查询条件
     * @return 班级概览分页信息
     */
    PageItem<List<ClassOverviewVO>> listClassOverviewPage(Integer page, Integer limit, Class clazz);
}
