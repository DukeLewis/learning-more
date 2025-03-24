package learning.more.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "周计划创建DTO")
public class WeeklyPlanCreateDTO {
    @Schema(description = "周计划名称")
    private String name;
    
    @Schema(description = "学校ID")
    private Long schoolId;
    
    @Schema(description = "班级ID")
    private Long classId;
    
    @Schema(description = "创建者")
    private String author;
} 