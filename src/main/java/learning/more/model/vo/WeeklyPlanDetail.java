package learning.more.model.vo;

import learning.more.model.domain.WeeklyPlan;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/5/1
 * @Copyright： https://github.com/DukeLewis
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeeklyPlanDetail extends WeeklyPlan {
    private String schoolName;

    private String className;

    private List<WeeklyPlanItemDetail> items;
}
