package learning.more.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import learning.more.model.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.StudentOverviewVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ASUS
* @description 针对表【student】的数据库操作Mapper
* @createDate 2024-11-29 12:11:28
* @Entity generator.domain.Student
*/
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 获取班级信息概览列表
     * @param classId 班级 id
     * @return 班级信息概览列表
     */
    List<StudentOverviewVO> listStudentOverview(@Param("classId") Long classId);

    /**
     * 获取班级信息概览分页列表
     * @param pageList 分页对象
     * @return 分页数据结果
     */
    Page<StudentOverviewVO> listStudentOverviewPage(@Param("pageList") Page<StudentOverviewVO> pageList);
}




