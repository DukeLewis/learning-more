package learning.more.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import learning.more.model.domain.Student;
import learning.more.dao.StudentDao;
import learning.more.dao.mapper.StudentMapper;
import learning.more.model.vo.StudentOverviewVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author ASUS
* @description 针对表【student】的数据库操作Service实现
* @createDate 2024-11-29 12:11:28
*/
@Service
public class StudentDaoImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentDao {

    @Override
    public List<StudentOverviewVO> listStudentOverview(Long classId) {
        return this.baseMapper.listStudentOverview(classId);
    }
}




