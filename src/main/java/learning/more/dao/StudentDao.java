package learning.more.dao;

import learning.more.model.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.StudentOverviewVO;

import java.util.List;

/**
* @author ASUS
* @description 针对表【student】的数据库操作Service
* @createDate 2024-11-29 12:11:28
*/
public interface StudentDao extends IService<Student> {

    /**
     * 获取班级信息概览列表
     * @param classId 班级 id
     * @return 班级信息概览列表
     */
    List<StudentOverviewVO> listStudentOverview(Long classId);

    /**
     * 获取班级信息概览分页列表
     * @param page 页码
     * @param limit 每页数量
     * @return 分页数据结果
     */
    PageItem<List<StudentOverviewVO>> listStudentOverviewPage(Integer page, Integer limit);
}
