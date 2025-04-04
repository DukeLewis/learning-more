package learning.more.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import learning.more.model.domain.CourseActivities;
import learning.more.model.domain.CourseObjectives;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/8
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@Tag(name = "课程信息详情VO")
public class CourseDetailVO {
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

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "完成阶段")
    private Integer activeStep;

    @Schema(description = "课程类型")
    private String type;

    @Schema(description = "课程目标")
    private List<CourseObjectives> objectives;

    @Schema(description = "课程活动")
    private List<CourseActivities> activities;

    public CourseDetailVO() {
        objectives = new ArrayList<>();
        activities = new ArrayList<>();
    }
}
