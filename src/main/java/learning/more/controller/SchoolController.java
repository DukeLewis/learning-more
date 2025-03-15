package learning.more.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import learning.more.model.dto.SchoolCreateDTO;
import learning.more.model.dto.SchoolUpdateDTO;
import learning.more.model.vo.CourseOverviewVO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SchoolOverviewVO;
import learning.more.model.vo.SuccessVO;
import learning.more.service.school.ISchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/3/9
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("/api/school")
public class SchoolController {
    @Resource
    private ISchoolService schoolService;

    @GetMapping("/listSchoolOverviewPage")
    public PageItem<List<SchoolOverviewVO>> listSchoolOverviewPage(@RequestParam("pageNum") @Parameter(description = "页码") Integer pageNum,
                                                                   @RequestParam("pageSize") @Parameter(description = "每页数量") Integer pageSize,
                                                                   @RequestParam(value = "searchString", required = false) @Parameter(description = "搜索字符串", required = false) String searchString) {
        return schoolService.listSchoolOverviewPage(pageNum, pageSize, searchString);
    }

    @PostMapping("/createSchool")
    public SuccessVO createSchool(@RequestBody @Parameter(description = "学校信息") SchoolCreateDTO schoolCreateDTO) {
        return schoolService.createSchool(schoolCreateDTO);
    }

    @PutMapping("/updateSchool")
    public SuccessVO updateSchool(@RequestBody @Parameter(description = "学校信息") SchoolUpdateDTO schoolUpdateDTO) {
        return schoolService.updateSchool(schoolUpdateDTO);
    }

    @DeleteMapping("/deleteSchool")
    public SuccessVO deleteSchool(@RequestParam("schoolId") @Parameter(description = "学校id") Long schoolId) {
        return schoolService.deleteSchool(schoolId);
    }
}
