package learning.more.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import learning.more.model.dto.WeeklyPlanCreateDTO;
import learning.more.model.dto.WeeklyPlanUpdateDTO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SuccessVO;
import learning.more.model.vo.WeeklyPlanOverviewVO;
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
    // todo 添加班级id和学校id作为筛选条件
    public PageItem<List<WeeklyPlanOverviewVO>> listWeeklyPlanPage(
            @RequestParam("pageNum") @Parameter(description = "页码") Integer pageNum,
            @RequestParam("pageSize") @Parameter(description = "每页数量") Integer pageSize,
            @RequestParam(value = "searchString", required = false) @Parameter(description = "搜索字符串") String searchString) {
        return weeklyPlanService.listWeeklyPlanPage(pageNum, pageSize, searchString);
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
}
