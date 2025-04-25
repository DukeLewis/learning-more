package learning.more.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName course_activities
 */
@TableName(value ="course_activities")
@Data
public class CourseActivities implements Serializable {
    /**
     * 活动唯一标识符
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
     * 活动名称
     */
    @TableField(value = "activity_name")
    private String activityName;

    /**
     * 活动描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 所需教具或材料
     */
    @TableField(value = "materials_needed")
    private String materialsNeeded;

    /**
     * 活动时长（分钟）
     */
    @TableField(value = "duration")
    private Integer duration;

    /**
     * 活动步骤
     */
    @TableField(value = "steps")
    private String steps;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
