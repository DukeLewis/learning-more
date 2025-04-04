package learning.more.model.vo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/3/29
 * @Copyright： https://github.com/DukeLewis
 */
@Tag(name = "学生信息VO")
@Data
public class StudentInfoVo {
    @Schema(description = "学生 id")
    private Long id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "班级名称")
    private String className;

    @Schema(description = "学生成绩列表")
    private List<StudentScoreVO> subjects;

    @Data
    public static class StudentScoreVO {
        @Schema(description = "课程名称")
        private String courseName;

        @Schema(description = "成绩")
        private Double score;
    }
}
