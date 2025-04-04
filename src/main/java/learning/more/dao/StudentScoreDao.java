package learning.more.dao;

import learning.more.model.domain.StudentScore;
import com.baomidou.mybatisplus.extension.service.IService;
import learning.more.model.vo.StudentInfoVo;

import java.util.List;

/**
* @author ASUS
* @description 针对表【student_score】的数据库操作Service
* @createDate 2024-11-29 12:11:28
*/
public interface StudentScoreDao extends IService<StudentScore> {
    /**
     * 根据学生id获取学生成绩
     * @param id 学生id
     * @return 学生成绩列表
     */
    List<StudentInfoVo.StudentScoreVO> listInfoByStudentId(Long id);
}
