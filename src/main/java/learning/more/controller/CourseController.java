package learning.more.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import learning.more.model.dto.CourseCreateDTO;
import learning.more.model.dto.CourseUpdateDTO;
import learning.more.model.dto.GenerateCourseDTO;
import learning.more.model.vo.*;
import learning.more.service.course.ICourseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/5
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("/api/course")
@CrossOrigin("${api.config.cross-origin}")
public class CourseController {
    @Resource
    private ICourseService courseService;

    @GetMapping("/listCourseOverviewPage")
    public PageItem<List<CourseOverviewVO>> listCourseOverviewPage(@RequestParam("page") @Parameter(description = "页码") Integer page,
                                                 @RequestParam("pageSize") @Parameter(description = "每页数量") Integer pageSize) {
        return courseService.listCourseOverviewPage(page, pageSize);
    }

    @GetMapping("/getCourseDetail")
    public CourseDetailVO getCourseDetail(@RequestParam("courseId") @Parameter(description = "课程id") Integer courseId) {
        return courseService.getCourseDetail(courseId);
    }

    @DeleteMapping("/deleteCourse")
    public SuccessVO deleteCourse(@RequestParam("courseId") @Parameter(description = "课程id") Integer courseId) {
        return courseService.deleteCourse(courseId);
    }

    @PutMapping("/updateCourse")
    public SuccessVO updateCourse(@RequestBody @Parameter(description = "课程信息") CourseUpdateDTO courseUpdateDTO) {
        return courseService.updateCourse(courseUpdateDTO);
    }

    @PostMapping("/createCourse")
    public SuccessVO createCourse(@RequestBody @Parameter(description = "课程信息") CourseCreateDTO courseCreateDTO) {
        return courseService.createCourse(courseCreateDTO);
    }

    // todo 使用大模型生成课程基本内容
    @PostMapping(value = "/generateCourseBaseInfo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter generateCourseBaseInfo(@RequestBody GenerateCourseDTO generateCourseDTO) {
        return courseService.generateCourseBaseInfo(generateCourseDTO);
    }

    // todo 使用大模型生成课程
}
