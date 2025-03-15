package learning.more.service.school;

import learning.more.model.vo.PageItem;
import learning.more.model.vo.SchoolOverviewVO;

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
}
