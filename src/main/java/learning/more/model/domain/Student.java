package learning.more.model.domain;

import com.alibaba.excel.annotation.ExcelProperty;
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
    public static final String FIELD_ID = "id";
    public static final String FIELD_TENANT_ID = "tenantId";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_GENDER = "gender";
    public static final String FIELD_AGE = "age";
    public static final String FIELD_CLASS_ID = "classId";
    public static final String FIELD_UPDATED_TIME = "updatedTime";
    public static final String FIELD_CREATED_TIME = "createdTime";
    public static final String FIELD_IS_DELETED = "isDeleted";
    public static final String FIELD_CLASS_NAME = "className";

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 学生名称
     */
    @TableField(value = "name")
    @ExcelProperty("学生姓名")
    private String name;

    /**
     * 性别
     */
    @TableField(value = "gender")
    @ExcelProperty("性别")
    private String gender;

    /**
     * 年龄
     */
    @TableField(value = "age")
    @ExcelProperty("年龄")
    private Integer age;

    /**
     * 所属班级id
     */
    @TableField(value = "class_id")
    @ExcelProperty("班级id")
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
    @ExcelProperty("班级名称")
    private String className;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
