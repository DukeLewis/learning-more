package learning.more.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 简单用户信息实体, 主要用于通过token验证后，将当前用户的信息存入上下文
 * @author：dukelewis
 * @date: 2024/9/22
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleUserVo {
    /** 用户 id */
    private Long id;

    /** 用户名*/
    private String username;
}
