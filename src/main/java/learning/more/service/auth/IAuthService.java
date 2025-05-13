package learning.more.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import learning.more.model.domain.User;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * 关于登录注册的接口
 */
public interface IAuthService extends IService<User> {
    Map<String,String> authorize(String username, String password);

    boolean register(String username, String password);

    /**
     * 分页查询用户
     * @param pageNum 页码
     * @param pageSize 页条数
     * @param name 用户名称
     * @param tenantName 机构名称
     * @param roleName 角色名称
     * @return 用户信息
     */
    PageItem<List<UserVO>> listPage(Integer pageNum, Integer pageSize, String name, String tenantName, String roleName);
}
