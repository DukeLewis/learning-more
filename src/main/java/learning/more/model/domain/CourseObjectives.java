package learning.more.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName course_objectives
 */
@TableName(value ="course_objectives")
@Data
public class CourseObjectives implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联的课程 ID
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 目标类型（如“知识目标”“技能目标”）
     */
    @TableField(value = "objective_type")
    private String objectiveType;

    /**
     * 目标描述
     */
    @TableField(value = "description")
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
