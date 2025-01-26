package learning.more.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import learning.more.model.vo.ClassInfoVO;
import learning.more.model.vo.ClassOverviewVO;
import learning.more.model.vo.SuccessVO;
import learning.more.service.clazz.IClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 班级信息控制器
 * @author：dukelewis
 * @date: 2024/12/29
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("/api/class")
@Tag(name = "1.班级模块")
public class ClassController {
    @Resource
    private IClassService classService;

    @GetMapping("/listClassOverview")
    @Operation(summary = "获取班级信息概览列表")
    public List<ClassOverviewVO> listClassOverview(){
        return classService.listClassOverview();
    }

    @GetMapping("/getClassInfo")
    @Operation(summary = "获取班级详细信息")
    public ClassInfoVO getClassInfo(@RequestParam("id") @Parameter(description = "班级 id") Long id){
        return classService.getClassInfo(id);
    }

    @DeleteMapping("/deleteClass")
    @Operation(summary = "删除班级")
    public SuccessVO deleteClass(@RequestParam("id") @Parameter(description = "班级 id") Long id){
        return classService.deleteClass(id);
    }

    @PutMapping("/updateClassInfo")
    @Operation(summary = "修改班级信息")
    public SuccessVO updateClassInfo(@RequestBody ClassInfoVO classInfoVO){
        return classService.updateClass(classInfoVO);
    }

    @GetMapping("/test")
    @Operation(summary = "测试模块联通")
    public String test(){
        return "hello";
    }
}
