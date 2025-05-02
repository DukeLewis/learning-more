package learning.more.model.vo;

import learning.more.model.domain.WeeklyPlanItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/5/1
 * @Copyright： https://github.com/DukeLewis
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeeklyPlanItemDetail extends WeeklyPlanItem {
    private String courseName;

    private Integer duration;
}
