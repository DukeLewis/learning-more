package learning.more.service.clazz;

import learning.more.model.domain.Class;
import learning.more.model.vo.ClassInfoVO;
import learning.more.model.vo.ClassOverviewVO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SuccessVO;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/12/29
 * @Copyright： https://github.com/DukeLewis
 */
public interface IClassService {
    /**
     * 获取班级信息概览
     * @param schoolId 学校 id
     * @return 班级信息概览列表
     */
    List<ClassOverviewVO> listClassOverview(Long schoolId);

    /**
     * 获取班级详细信息
     * @param id 班级 id
     * @return 班级详细信息
     */
    ClassInfoVO getClassInfo(Long id);

    /**
     * 删除班级
     * @param id 班级 id
     * @return 操作结果
     */
    SuccessVO deleteClass(Long id);

    /**
     * 更新班级信息
     * @param classInfoVO 新班级信息
     * @return 操作结果
     */
    SuccessVO updateClass(ClassInfoVO classInfoVO);

    /**
     * 获取班级信息概览分页列表
     * @param page 页码
     * @param limit 每页数量
     * @param clazz 查询条件
     * @return 分页列表
     */
    PageItem<List<ClassOverviewVO>> listClassOverviewPage(Integer page, Integer limit, Class clazz);
}
