package learning.more.service.student.impl;

import jakarta.annotation.Resource;
import learning.more.dao.StudentDao;
import learning.more.model.vo.StudentOverviewVO;
import learning.more.service.student.IStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/1/4
 * @Copyright： https://github.com/DukeLewis
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Resource
    private StudentDao studentDao;

    @Override
    public List<StudentOverviewVO> listStudentOverview(Long classId) {
        return studentDao.listStudentOverview(classId);
    }
}
