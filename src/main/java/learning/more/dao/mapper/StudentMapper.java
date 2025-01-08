package learning.more.dao.mapper;

import learning.more.model.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}




