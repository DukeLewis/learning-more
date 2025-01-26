package learning.more.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/1/21
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "分页数据")
public class PageItem<T> {
    @Schema(description = "总数量")
    private Long total;

    @Schema(description = "总页数")
    private Long pageSize;

    @Schema(description = "当前页码")
    private Integer pageNum;

    @Schema(description = "数据")
    private T data;
}
