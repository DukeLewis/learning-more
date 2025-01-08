package learning.more.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 班级信息概览
 * @author：dukelewis
 * @date: 2024/12/29
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "班级信息概览VO")
public class ClassOverviewVO {
    @Schema(description = "班级 id")
    private Long id;

    @Schema(description = "班级名称")
    private String className;

    @Schema(description = "学生数量")
    private Integer studentCount;

    @Schema(description = "创建时间")
    private Date createTime;
}
