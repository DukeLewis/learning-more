package learning.more.service.auth.impl;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.security.auth.message.AuthStatus;

import learning.more.common.AppResult;
import learning.more.common.enums.ResultCode;
import learning.more.dao.mapper.UserMapper;
import learning.more.exception.ApplicationException;
import learning.more.model.domain.User;
import learning.more.model.entity.AuthUserEntity;
import learning.more.service.auth.IAuthService;
import learning.more.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class AuthService extends ServiceImpl<UserMapper, User> implements IAuthService {


    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder bcryptPasswordEncoder;


    @Override
    public Map<String,String> authorize(String username, String password){
        log.info("开始验证----");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_LOGIN));
        }
        //获取验证成功的用户，包含账号以及id
        AuthUserEntity user = (AuthUserEntity) authenticate.getPrincipal();
        username = user.getUsername();
        log.info("开始封装返回的jwt");
        //开始封装返回的token
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username", username);
        chaim.put("id", user.getId());
        chaim.put("tenantId", user.getTenantId());
        Map<String, String> map = new HashMap<>();
        String jwtToken = JwtUtils.encode(username, -1L, chaim);
        map.put("msg", AuthStatus.SUCCESS.toString());
        map.put("token", jwtToken);
        return map;
    }

    @Override
    public boolean register(String username, String password) {
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_USER_PWD_NULL));
        }
        if(this.lambdaQuery().eq(User::getUsername, username).count() > 0){
            throw new ApplicationException(AppResult.failed(ResultCode.FAILED_USER_EXISTS));
        }
        Date date = new Date();
        return this.save(User.builder()
                .username(username)
                .password(bcryptPasswordEncoder.encode(password))
                .createdTime(date)
                .updatedTime(date)
                .isDeleted(0).build());
    }
}
