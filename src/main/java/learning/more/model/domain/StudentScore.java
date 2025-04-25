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
 * @TableName student_score
 */
@TableName(value ="student_score")
@Data
public class StudentScore implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生id
     */
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 课程id
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 分数
     */
    @TableField(value = "score")
    private Double score;

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
