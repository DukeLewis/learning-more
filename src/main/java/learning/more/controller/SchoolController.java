package learning.more.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import learning.more.model.vo.CourseOverviewVO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SchoolOverviewVO;
import learning.more.service.school.ISchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                                                                   @RequestParam("searchString") @Parameter(description = "搜索字符串") String searchString) {
        return schoolService.listSchoolOverviewPage(pageNum, pageSize, searchString);
    }
}
