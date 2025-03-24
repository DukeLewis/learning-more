package learning.more.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "周计划概览VO")
public class WeeklyPlanOverviewVO {
    @Schema(description = "周计划ID")
    private Long id;

    @Schema(description = "周计划名称")
    private String name;

    @Schema(description = "学校名称")
    private String schoolName;

    @Schema(description = "班级名称")
    private String className;

    @Schema(description = "创建者")
    private String author;

    @Schema(description = "更新时间")
    private Date updatedTime;
}
