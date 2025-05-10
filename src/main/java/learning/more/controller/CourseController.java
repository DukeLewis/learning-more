package learning.more.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import learning.more.model.domain.Course;
import learning.more.model.domain.CourseActivities;
import learning.more.model.domain.CourseObjectives;
import learning.more.model.dto.CourseCreateDTO;
import learning.more.model.dto.CourseUpdateDTO;
import learning.more.model.dto.GenerateCourseDTO;
import learning.more.model.vo.*;
import learning.more.service.auth.UserHolder;
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
@Tag(name = "课程信息管理")
public class CourseController {
    @Resource
    private ICourseService courseService;

    @GetMapping("/listCourseOverviewPage")
    @Operation(summary = "分页获取课程概览信息")
    public PageItem<List<CourseOverviewVO>> listCourseOverviewPage(@RequestParam("page") @Parameter(description = "页码") Integer page,
                                                                   @RequestParam("pageSize") @Parameter(description = "每页数量") Integer pageSize,
                                                                   Course course) {
        course.setTenantId(UserHolder.getTenantId());
        return courseService.listCourseOverviewPage(page, pageSize, course);
    }

    @GetMapping("/getCourseDetail")
    @Operation(summary = "获取课程详情")
    public CourseDetailVO getCourseDetail(@RequestParam("courseId") @Parameter(description = "课程id") Integer courseId) {
        return courseService.getCourseDetail(courseId);
    }

    @DeleteMapping("/deleteCourse")
    @Operation(summary = "删除课程")
    public SuccessVO deleteCourse(@RequestParam("courseId") @Parameter(description = "课程id") Integer courseId) {
        return courseService.deleteCourse(courseId);
    }

    @PutMapping("/updateCourse")
    @Operation(summary = "更新课程")
    public SuccessVO updateCourse(@RequestBody @Parameter(description = "课程信息") CourseUpdateDTO courseUpdateDTO) {
        return courseService.updateCourse(courseUpdateDTO);
    }

    @PostMapping("/createCourse")
    @Operation(summary = "创建课程")
    public SuccessVO<Long> createCourse(@RequestBody @Parameter(description = "课程信息") CourseCreateDTO courseCreateDTO) {
        return courseService.createCourse(courseCreateDTO);
    }

    @PostMapping("/createCourseFirst")
    @Operation(summary = "创建课程第一步信息")
    public SuccessVO<Long> createCourseFirst(@RequestBody @Parameter(description = "课程信息") CourseCreateDTO courseCreateDTO) {
        return courseService.createCourseFirst(courseCreateDTO);
    }

    @PutMapping("/updateCourseFirst")
    @Operation(summary = "更新课程第一步信息")
    public SuccessVO<Long> updateCourseFirst(@RequestBody @Parameter(description = "课程信息") CourseUpdateDTO courseUpdateDTO) {
        return courseService.updateCourseFirst(courseUpdateDTO);
    }

    @Operation(summary = "使用大模型生成课程基本内容（第一步）")
    @GetMapping(value = "/generateCourseBaseInfo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter generateCourseBaseInfo(GenerateCourseDTO generateCourseDTO) {
        return courseService.generateCourseBaseInfo(generateCourseDTO);
    }

    @Operation(summary = "使用大模型生成课程目标（第二步）")
    @GetMapping(value = "/generateCourseObjectives", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter generateCourseObjectives(@RequestParam Long courseId) {
        return courseService.generateCourseObjectives(courseId);
    }

    @PostMapping("/createOrUpdateCourseSecond")
    @Operation(summary = "创建课程第二步信息")
    public SuccessVO<List<CourseObjectives>> createOrUpdateCourseSecond(@RequestBody @Parameter(description = "课程目标信息") List<CourseObjectives> courseObjectivesList) {
        return courseService.createOrUpdateCourseSecond(courseObjectivesList);
    }

    @GetMapping("/generateCourseActivities")
    @Operation(summary = "使用大模型生成课程活动信息（第三步）")
    public SseEmitter generateCourseActivities(@RequestParam Long courseId) {
        return courseService.generateCourseActivities(courseId);
    }

    @PostMapping("/createOrUpdateCourseThird")
    @Operation(summary = "创建课程第三步信息")
    public SuccessVO<List<CourseActivities>> createOrUpdateCourseThird(@RequestBody @Parameter(description = "课程活动信息") List<CourseActivities> courseActivitiesList) {
        return courseService.createOrUpdateCourseThird(courseActivitiesList);
    }

}
