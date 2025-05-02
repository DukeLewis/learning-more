package learning.more.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName class
 */
@TableName(value ="class")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Class implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 班级管理老师 id
     */
    @TableField(value = "teacher_id")
    private Long teacherId;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 学校id
     */
    @TableField(value = "school_id")
    private Long schoolId;

    /**
     * 班级名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 班级人数
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 所在学校
     */
    @TableField(value = "school")
    private String school;

    /**
     * 年级
     */
    @TableField(value = "grade")
    private String grade;

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
