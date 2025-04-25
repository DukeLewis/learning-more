package learning.more.controller;

import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import learning.more.model.domain.Student;
import learning.more.model.vo.*;
import learning.more.service.auth.UserHolder;
import learning.more.service.student.IStudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 学生信息控制器
 * @author：dukelewis
 * @date: 2025/1/2
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("/api/student")
@Tag(name = "2.学生模块")
@Validated
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
                                                                     @RequestParam("limit") @Parameter(description = "每页数量") Integer limit,
                                                                     Student student){
        student.setTenantId(UserHolder.getTenantId());
        return studentService.listStudentOverviewPage(page, limit, student);
    }

    @GetMapping("/getStudentInfo/{id}")
    @Operation(summary = "获取学生信息")
    public StudentInfoVo getStudentInfo(@PathVariable("id") @Min(0) @NotNull Long id){
        return studentService.getStudentInfo(id);
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

    @GetMapping("/download-template")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        // 设置响应头，指示浏览器下载文件
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data_import_template.xlsx");

        List<Student> sampleData = new ArrayList<>();
        Student student = new Student();
        student.setAge(18);
        student.setName("John Doe");
        student.setGender("男");
        student.setClassName("A101");
        sampleData.add(student);

        Set<String> excludeColumnFiledNames = new HashSet<>();
        excludeColumnFiledNames.add(Student.FIELD_CREATED_TIME);
        excludeColumnFiledNames.add(Student.FIELD_UPDATED_TIME);
        excludeColumnFiledNames.add(Student.FIELD_IS_DELETED);
        excludeColumnFiledNames.add(Student.FIELD_ID);
        excludeColumnFiledNames.add(Student.FIELD_TENANT_ID);
//        excludeColumnFiledNames.add(Student.FIELD_CLASS_ID);

        // 使用 EasyExcel 生成 Excel 文件
        OutputStream outputStream = response.getOutputStream();
        EasyExcel.write(outputStream, Student.class)
                .excludeColumnFieldNames(excludeColumnFiledNames)
                .sheet("学生数据导入模板")
                .doWrite(sampleData);
        outputStream.flush();
    }

    @PostMapping("/import")
    public SuccessVO<Void> importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return studentService.importExcel(file);
    }
}
