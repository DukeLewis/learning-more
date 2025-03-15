package learning.more.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/9
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "活动步骤实体")
public class ActivityStepEntity {
    @Schema(description = "课程 id")
    private Long courseId;

    @Schema(description = "活动步骤标题")
    private String title;

    @Schema(description = "活动步骤时长")
    private Integer duration;

    @Schema(description = "活动步骤内容")
    private String content;

    @Schema(description = "活动步骤提示")
    private String tips;

    @Schema(description = "活动步骤关键点")
    private List<String> keyPoints;
}
