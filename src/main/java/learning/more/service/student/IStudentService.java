package learning.more.service.student;

import learning.more.model.domain.Student;
import learning.more.model.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/1/4
 * @Copyright： https://github.com/DukeLewis
 */
public interface IStudentService {
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
     * @param student 学生信息
     * @return 分页数据结果
     */
    PageItem<List<StudentOverviewVO>> listStudentOverviewPage(Integer page, Integer limit, Student student);

    /**
     * 创建学生信息
     * @param studentInfoDTO 学生信息
     * @return 操作成功响应
     */
    SuccessVO createStudent(StudentInfoDTO studentInfoDTO);

    /**
     * 删除学生信息
     * @param studentId 学生 id
     * @return 操作成功响应
     */
    SuccessVO deleteStudent(Long studentId);

    /**
     * 更新学生信息
     * @param studentInfoDTO 学生信息
     * @return 操作成功响应
     */
    SuccessVO updateStudent(StudentInfoDTO studentInfoDTO);

    /**
     * 获取学生信息
     * @param id 学生 id
     * @return 学生信息
     */
    StudentInfoVo getStudentInfo(Long id);

    /**
     * 导入学生信息
     * @param file excel 文件
     * @return 操作成功响应
     */
    SuccessVO<Void> importExcel(MultipartFile file) throws IOException;
}
