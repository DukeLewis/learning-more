package learning.more.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import learning.more.model.dto.WeeklyPlanCreateDTO;
import learning.more.model.dto.WeeklyPlanUpdateDTO;
import learning.more.model.vo.*;
import learning.more.service.weeklyplan.IWeeklyPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/3/9
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("/api/weeklyPlan")
public class WeeklyPlanController {
    @Resource
    private IWeeklyPlanService weeklyPlanService;

    @GetMapping("/listWeeklyPlanPage")
    public PageItem<List<WeeklyPlanOverviewVO>> listWeeklyPlanPage(
            @RequestParam("pageNum") @Parameter(description = "页码") Integer pageNum,
            @RequestParam("pageSize") @Parameter(description = "每页数量") Integer pageSize,
            @RequestParam(value = "classId", required = false) @Parameter(description = "班级id") Long classId,
            @RequestParam(value = "schoolId", required = false) @Parameter(description = "学校id") Long schoolId,
            @RequestParam(value = "searchString", required = false) @Parameter(description = "搜索字符串") String searchString) {
        return weeklyPlanService.listWeeklyPlanPage(pageNum, pageSize, classId, schoolId, searchString);
    }

    @PostMapping("/createWeeklyPlan")
    public SuccessVO createWeeklyPlan(@RequestBody @Parameter(description = "周计划信息") WeeklyPlanCreateDTO weeklyPlanCreateDTO) {
        return weeklyPlanService.createWeeklyPlan(weeklyPlanCreateDTO);
    }

    @PutMapping("/updateWeeklyPlan")
    public SuccessVO updateWeeklyPlan(@RequestBody @Parameter(description = "周计划信息") WeeklyPlanUpdateDTO weeklyPlanUpdateDTO) {
        return weeklyPlanService.updateWeeklyPlan(weeklyPlanUpdateDTO);
    }

    @DeleteMapping("/deleteWeeklyPlan")
    public SuccessVO deleteWeeklyPlan(@RequestParam("planId") @Parameter(description = "计划id") Long planId) {
        return weeklyPlanService.deleteWeeklyPlan(planId);
    }

    @GetMapping("/getWeeklyPlanDetail")
    public WeeklyPlanDetail getWeeklyPlanDetail(@RequestParam("planId") @Parameter(description = "计划id") Long planId) {
        return weeklyPlanService.getWeeklyPlanDetail(planId);
    }
}
