package learning.more.service.student.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
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
import learning.more.service.auth.UserHolder;
import learning.more.service.student.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/1/4
 * @Copyright： https://github.com/DukeLewis
 */
@Service
@Slf4j
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
    public PageItem<List<StudentOverviewVO>> listStudentOverviewPage(Integer page, Integer limit, Student student) {
        return studentDao.listStudentOverviewPage(page, limit, student);
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

    @Override
    public SuccessVO<Void> importExcel(MultipartFile file) throws IOException {
        // 使用 EasyExcel 读取文件
        List<Student> userList = EasyExcel.read(file.getInputStream(), Student.class, new ReadListener<Student>() {
                    /**
                     * 单次缓存的数据量
                     */
                    public static final int BATCH_COUNT = 100;
                    /**
                     *临时存储
                     */
                    private List<Student> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        saveData();
                    }

                    @Override
                    public void invoke(Student student, AnalysisContext analysisContext) {
                        student.setTenantId(UserHolder.getTenantId());
                        cachedDataList.add(student);
                        if (cachedDataList.size() >= BATCH_COUNT) {
                            saveData();
                            // 存储完成清理 list
                            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                        }
                    }

                    @Override
                    public void onException(Exception exception, AnalysisContext context) throws Exception {
                        log.error("读取失败：{}", exception.getMessage());
                        ReadListener.super.onException(exception, context);
                    }

                    /**
                     * 加上存储数据库
                     */
                    private void saveData() {
                        studentDao.saveBatch(cachedDataList);
                        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
                        log.info("存储数据库成功！");
                    }
                })
                .sheet()
                .doReadSync();

        return SuccessVO.successNotData();
    }
}
