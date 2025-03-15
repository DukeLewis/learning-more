package learning.more.model.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import learning.more.model.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/7
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "课程信息概览VO")
public class CourseOverviewVO {
    @Schema(description = "课程 id")
    private Long id;

    @Schema(description = "课程名称")
    private String courseName;

    @Schema(description = "课程时长")
    private Integer duration;

    @Schema(description = "课程描述")
    private String courseDescription;

    @Schema(description = "更新时间")
    private Date updateTime;

    public static List<CourseOverviewVO> coursePage2VO(Page<Course> coursePage) {
        List<CourseOverviewVO> courseOverviewVOS = new ArrayList<>();
        coursePage.getRecords().forEach(course -> {
            courseOverviewVOS.add(
                    new CourseOverviewVO(course.getId(), course.getName(), course.getDuration(),
                            course.getDescription(), course.getUpdatedTime())
            );
        });
        return courseOverviewVOS;
    }
}
