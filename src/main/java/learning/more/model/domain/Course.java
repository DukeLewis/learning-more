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
 * @TableName course
 */
@TableName(value ="course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 教学老师id
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     * 课程类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 课程描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 适用年龄范围（如“3-4岁”）
     */
    @TableField(value = "age_group")
    private String ageGroup;

    /**
     * 单节课时长（分钟）
     */
    @TableField(value = "duration")
    private Integer duration;

    /**
     * 课程总节数
     */
    @TableField(value = "total_sessions")
    private Integer totalSessions;

    /**
     * 课程开始日期
     */
    @TableField(value = "start_date")
    private Date startDate;

    /**
     * 课程结束日期
     */
    @TableField(value = "end_date")
    private Date endDate;

    /**
     * 课程最大学生人数
     */
    @TableField(value = "max_students")
    private Integer maxStudents;

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
