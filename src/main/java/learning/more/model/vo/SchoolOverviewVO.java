package learning.more.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/3/9
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "学校信息概览VO")
public class SchoolOverviewVO {
    @Schema(description = "学校 id")
    private Long id;

    @Schema(description = "学校名称")
    private String name;

    @Schema(description = "学校位置")
    private String position;

    @Schema(description = "学校人数")
    private Integer quantity;
}
