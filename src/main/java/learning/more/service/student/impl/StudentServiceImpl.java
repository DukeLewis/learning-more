package learning.more.service.student.impl;

import jakarta.annotation.Resource;
import learning.more.common.AppResult;
import learning.more.common.enums.ResultCode;
import learning.more.dao.ClassDao;
import learning.more.dao.StudentDao;
import learning.more.dao.StudentScoreDao;
import learning.more.exception.ApplicationException;
import learning.more.model.domain.Class;
import learning.more.model.domain.Student;
import learning.more.model.vo.*;
import learning.more.service.student.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Resource
    private ClassDao classDao;

    @Resource
    private StudentScoreDao studentScoreDao;

    @Override
    public List<StudentOverviewVO> listStudentOverview(Long classId) {
        return studentDao.listStudentOverview(classId);
    }

    @Override
    public PageItem<List<StudentOverviewVO>> listStudentOverviewPage(Integer page, Integer limit) {
        return studentDao.listStudentOverviewPage(page, limit);
    }

    @Override
    public SuccessVO createStudent(StudentInfoDTO studentInfoDTO) {
        if (studentInfoDTO == null) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Class aClass = classDao.lambdaQuery()
                .select(Class::getId)
                .eq(Class::getName, studentInfoDTO.getClassName())
                .eq(Class::getIsDeleted, 0).one();
        Date date = new Date();
        boolean save = studentDao.save(Student.builder()
                .name(studentInfoDTO.getName())
                .gender(studentInfoDTO.getGender())
                .age(studentInfoDTO.getAge())
                .classId(aClass == null ? null : aClass.getId())
                .createdTime(date)
                .updatedTime(date)
                .isDeleted(0)
                .build());
        if (!save) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }

    @Override
    public SuccessVO deleteStudent(Long studentId) {
        if (studentId == null || studentId <= 0) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        boolean removed = studentDao.removeById(studentId);
        if (!removed) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }

    @Override
    public SuccessVO updateStudent(StudentInfoDTO studentInfoDTO) {
        if (studentInfoDTO == null || studentInfoDTO.getId() == null || studentInfoDTO.getId() <= 0) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_PARAMS_VALIDATE));
        }
        Student student = studentDao.getById(studentInfoDTO.getId());
        Class aClass = classDao.lambdaQuery()
                .select(Class::getId)
                .eq(Class::getName, studentInfoDTO.getClassName())
                .eq(Class::getIsDeleted, 0).one();
        boolean updated = studentDao.updateById(Student.builder()
                .id(studentInfoDTO.getId())
                .name(studentInfoDTO.getName())
                .gender(studentInfoDTO.getGender())
                .age(studentInfoDTO.getAge())
                .classId((aClass == null || Objects.equals(student.getClassId(), aClass.getId())) ? null : aClass.getId())
                .updatedTime(new Date())
                .isDeleted(0)
                .build());
        if (!updated) {
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED));
        }
        return SuccessVO.successNotData();
    }

    @Override
    public StudentInfoVo getStudentInfo(Long id) {
        Student student = studentDao.lambdaQuery()
                .eq(Student::getId, id)
                .select(Student::getId, Student::getName, Student::getGender,
                        Student::getAge, Student::getClassId)
                .one();
        Class aClass = classDao.lambdaQuery()
                .eq(Class::getId, student.getClassId())
                .eq(Class::getIsDeleted, 0)
                .select(Class::getName)
                .one();
        StudentInfoVo studentInfoVo = new StudentInfoVo();
        BeanUtils.copyProperties(student, studentInfoVo);
        studentInfoVo.setClassName(aClass == null ? null : aClass.getName());
        List<StudentInfoVo.StudentScoreVO> studentScoreVOs = studentScoreDao.listInfoByStudentId(id);
        studentInfoVo.setSubjects(studentScoreVOs);
        return studentInfoVo;
    }
}
