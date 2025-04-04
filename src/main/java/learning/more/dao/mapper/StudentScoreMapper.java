package learning.more.dao.mapper;

import learning.more.model.domain.StudentScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learning.more.model.vo.StudentInfoVo;

import java.util.List;

/**
* @author ASUS
* @description 针对表【student_score】的数据库操作Mapper
* @createDate 2024-11-29 12:11:28
* @Entity generator.domain.StudentScore
*/
public interface StudentScoreMapper extends BaseMapper<StudentScore> {
    /**
     * 根据学生id获取学生成绩信息
     * @param id 学生id
     * @return 学生成绩信息
     */
    List<StudentInfoVo.StudentScoreVO> listInfoByStudentId(Long id);
}




