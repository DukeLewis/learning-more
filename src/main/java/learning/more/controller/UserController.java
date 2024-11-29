package learning.more.controller;

import jakarta.annotation.Resource;
import learning.more.common.AppResult;
import learning.more.model.dto.AuthDTO;
import learning.more.model.vo.AuthVO;
import learning.more.service.auth.IAuthService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
