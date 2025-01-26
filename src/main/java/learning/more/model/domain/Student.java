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
 * @TableName student
 */
@TableName(value ="student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 所属班级id
     */
    @TableField(value = "class_id")
    private Long classId;

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
