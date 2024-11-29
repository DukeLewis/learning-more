package learning.more.service.auth;

import learning.more.model.vo.SimpleUserVo;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @description: 存储当前线程的用户上下文信息
 * @author：dukelewis
 * @date: 2024/9/22
 * @Copyright： https://github.com/DukeLewis
 */
public class UserHolder {

    /**
     * 获取当前用户上下文信息
     * @return 用户上下文信息
     */
    public static SimpleUserVo getUser() {
        return (SimpleUserVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前用户 id
     * @return 用户 id
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前用户名
     * @return 用户名
     */
    public static String getUsername() {
        return getUser().getUsername();
    }
}
