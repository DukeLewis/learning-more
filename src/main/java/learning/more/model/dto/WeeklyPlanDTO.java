package learning.more.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "周计划DTO")
public class WeeklyPlanDTO {
    @Schema(description = "计划id")
    private Long id;

    @Schema(description = "计划名称")
    private String name;

    @Schema(description = "计划内容")
    private String content;

    @Schema(description = "开始时间")
    private Date startTime;

    @Schema(description = "结束时间")
    private Date endTime;

    @Schema(description = "状态")
    private Integer status;
}
