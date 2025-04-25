package learning.more.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/4/4
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "课程活动步骤项")
public class ActivityStepItem {
    @Schema(description = "标题")
    private String title;

    @Schema(description = "时长")
    private Integer duration;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "提示")
    private String tips;

    @Schema(description = "关键点")
    private List<String> keyPoints;
}
