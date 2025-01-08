package learning.more.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/12/30
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "班级详细信息VO")
public class ClassInfoVO {
    @Schema(description = "班级 id")
    private Long id;

    @Schema(description = "管理老师名称")
    private String teacherName;

    @Schema(description = "班级名称")
    private String className;

    @Schema(description = "学生数量")
    private Integer studentCount;

    @Schema(description = "所在学校")
    private String school;

    @Schema(description = "所在年级")
    private String grade;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updatedTime;
}
