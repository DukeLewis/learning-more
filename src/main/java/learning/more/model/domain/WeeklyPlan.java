package learning.more.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 *
 * @TableName weekly_plan
 */
@TableName(value ="weekly_plan")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyPlan implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 周计划名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 周计划所属学校id
     */
    @TableField(value = "school_id")
    private Long schoolId;

    /**
     * 周计划所属班级id
     */
    @TableField(value = "class_id")
    private Long classId;

    /**
     * 周计划创建者
     */
    @TableField(value = "author")
    private String author;

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
