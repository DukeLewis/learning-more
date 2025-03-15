package learning.more.service.school;

import learning.more.model.dto.SchoolCreateDTO;
import learning.more.model.dto.SchoolUpdateDTO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SchoolOverviewVO;
import learning.more.model.vo.SuccessVO;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/12/29
 * @Copyright： https://github.com/DukeLewis
 */
public interface ISchoolService {

    /**
     * 获取学校概览列表
     *
     * @param pageNum    页码
     * @param pageSize   每页数量
     * @param searchString 搜索学校名称
     * @return 学校概览列表
     */
    PageItem<List<SchoolOverviewVO>> listSchoolOverviewPage(Integer pageNum, Integer pageSize, String searchString);

    /**
     * 创建学校
     * @param schoolCreateDTO 学校创建信息
     * @return 操作成功响应
     */
    SuccessVO createSchool(SchoolCreateDTO schoolCreateDTO);

    /**
     * 更新学校信息
     * @param schoolUpdateDTO 学校更新信息
     * @return 操作成功响应
     */
    SuccessVO updateSchool(SchoolUpdateDTO schoolUpdateDTO);

    /**
     * 删除学校
     * @param schoolId 学校id
     * @return 操作成功响应
     */
    SuccessVO deleteSchool(Long schoolId);
}
