package learning.more.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import learning.more.model.vo.ClassOverviewVO;
import learning.more.model.vo.StudentOverviewVO;
import learning.more.service.student.IStudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Operation(summary = "获取班级信息概览列表")
    public List<StudentOverviewVO> listStudentOverview(@RequestParam("classId") @Parameter(description = "班级 id") Long classId){
        return studentService.listStudentOverview(classId);
    }
}
