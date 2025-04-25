package learning.more.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName course_resources
 */
@TableName(value ="course_resources")
@Data
public class CourseResources implements Serializable {
    /**
     * 	资源唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 	关联的课程 ID
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 资源名称
     */
    @TableField(value = "resource_name")
    private String resourceName;

    /**
     * 资源类型（如“教具”“多媒体”）
     */
    @TableField(value = "resource_type")
    private String resourceType;

    /**
     * 资源文件路径（如图片、视频）
     */
    @TableField(value = "file_path")
    private String filePath;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
