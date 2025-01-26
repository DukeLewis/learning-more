package learning.more.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.StudentInfoDTO;
import learning.more.model.vo.StudentOverviewVO;
import learning.more.model.vo.SuccessVO;
import learning.more.service.student.IStudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 学生信息控制器
 * @author：dukelewis
 * @date: 2025/1/2
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("/api/student")
@Tag(name = "2.学生模块")
public class StudentController {
    @Resource
    private IStudentService studentService;

    @GetMapping("/listStudentOverview")
    @Operation(summary = "获取班级的学生信息概览列表")
    public List<StudentOverviewVO> listStudentOverview(@RequestParam("classId") @Parameter(description = "班级 id") Long classId){
        return studentService.listStudentOverview(classId);
    }

    @GetMapping("/listStudentOverviewPage")
    @Operation(summary = "获取学生信息列表")
    public PageItem<List<StudentOverviewVO>> listStudentOverviewPage(@RequestParam("page") @Parameter(description = "页码") Integer page,
                                                                     @RequestParam("limit") @Parameter(description = "每页数量") Integer limit){
        return studentService.listStudentOverviewPage(page, limit);
    }

    @PostMapping("/createStudent")
    @Operation(summary = "创建学生信息")
    public SuccessVO createStudent(@RequestBody StudentInfoDTO studentInfoDTO){
        return studentService.createStudent(studentInfoDTO);
    }

    @DeleteMapping("/deleteStudent")
    @Operation(summary = "删除学生信息")
    public SuccessVO deleteStudent(@RequestParam("studentId") @Parameter(description = "学生 id") Long studentId){
        return studentService.deleteStudent(studentId);
    }

    @PutMapping("/updateStudent")
    @Operation(summary = "更新学生信息")
    public SuccessVO updateStudent(@RequestBody StudentInfoDTO studentInfoDTO){
        return studentService.updateStudent(studentInfoDTO);
    }
}
