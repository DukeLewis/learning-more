package learning.more.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/11
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "课程信息更新DTO")
public class CourseUpdateDTO {
    @Schema(description = "课程 id")
    private Long id;

    @Schema(description = "课程名称")
    private String courseName;

    @Schema(description = "课程时长")
    private Integer duration;

    @Schema(description = "课程描述")
    private String courseDescription;

    @Schema(description = "年龄段")
    private String ageGroup;

    @Schema(description = "最大学生数")
    private Integer maxStudents;

    @Schema(description = "总课次")
    private Integer totalSessions;

    @Schema(description = "开始时间")
    private Date startTime;

    @Schema(description = "结束时间")
    private Date endTime;

    @Schema(description = "课程目标")
    private String objectives;

    @Schema(description = "课程活动")
    private String activities;
}
