package learning.more.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import learning.more.common.AppResult;
import learning.more.model.domain.Role;
import learning.more.model.domain.User;
import learning.more.model.dto.AuthDTO;
import learning.more.model.vo.AuthVO;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.UserVO;
import learning.more.service.auth.IAuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/11/29
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin("${api.config.cross-origin}")
public class UserController {

    @Resource
    private IAuthService authService;


    @PostMapping("/login")
    public AppResult<AuthVO> login(@RequestBody AuthDTO authDTO){
        Map<String, String> map = authService.authorize(authDTO.getUsername(), authDTO.getPassword());
        AuthVO authVO = new AuthVO(map.get("msg"), map.get("token"));
        return AppResult.success(authVO);
    }

    @PostMapping("/register")
    public AppResult<AuthVO> register(@RequestBody AuthDTO authDTO){
        boolean flg = authService.register(authDTO.getUsername(), authDTO.getPassword());
        AuthVO authVO = new AuthVO(flg == true ? "注册成功" : "注册失败");
        return AppResult.success(authVO);
    }

    @GetMapping("/listPage")
    public PageItem<List<UserVO>> listPage(@RequestParam("pageNum") @Parameter(description = "页码") Integer pageNum,
                                           @RequestParam("pageSize") @Parameter(description = "每页数量") Integer pageSize,
                                           @RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "tenantName", required = false) String tenantName,
                                           @RequestParam(value = "roleName", required = false) String roleName) {
        return authService.listPage(pageNum, pageSize, name, tenantName, roleName);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
