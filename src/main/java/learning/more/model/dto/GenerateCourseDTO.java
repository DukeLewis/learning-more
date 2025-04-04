package learning.more.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/25
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "生成课程所需信息DTO")
public class GenerateCourseDTO {
    @Schema(description = "年龄段")
    private String ageGroup;

    @Schema(description = "最大学生数")
    private Integer maxStudents;

    @Schema(description = "课程类型")
    private String type;
}
