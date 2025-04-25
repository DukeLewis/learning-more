package learning.more.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName weekly_plan_item
 */
@TableName(value ="weekly_plan_item")
@Data
public class WeeklyPlanItem implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 课程
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 周计划id
     */
    @TableField(value = "weekly_plan_id")
    private Integer weeklyPlanId;

    /**
     * 周几(1-7)
     */
    @TableField(value = "day_of_week")
    private Integer dayOfWeek;

    /**
     * 序号
     */
    @TableField(value = "sort_num")
    private Integer sortNum;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
