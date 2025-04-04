package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.model.domain.StudentScore;
import learning.more.dao.mapper.StudentScoreMapper;
import learning.more.dao.StudentScoreDao;
import learning.more.model.vo.StudentInfoVo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
* @author ASUS
* @description 针对表【student_score】的数据库操作Service实现
* @createDate 2024-11-29 12:11:28
*/
@Service
public class StudentScoreDaoImpl extends ServiceImpl<StudentScoreMapper, StudentScore>
    implements StudentScoreDao {

    @Override
    public List<StudentInfoVo.StudentScoreVO> listInfoByStudentId(Long id) {
        List<StudentInfoVo.StudentScoreVO> studentScoreVOS = this.baseMapper.listInfoByStudentId(id);
        return studentScoreVOS == null ? Collections.emptyList() : studentScoreVOS;
    }
}




