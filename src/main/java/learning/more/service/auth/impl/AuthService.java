package learning.more.service.auth.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.security.auth.message.AuthStatus;

import learning.more.common.AppResult;
import learning.more.common.enums.ResultCode;
import learning.more.dao.RoleDao;
import learning.more.dao.TenantDao;
import learning.more.dao.UserDao;
import learning.more.dao.UserRoleRelationDao;
import learning.more.dao.mapper.UserMapper;
import learning.more.exception.ApplicationException;
import learning.more.model.domain.Role;
import learning.more.model.domain.Tenant;
import learning.more.model.domain.User;
import learning.more.model.domain.UserRoleRelation;
import learning.more.model.entity.AuthUserEntity;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.UserVO;
import learning.more.service.auth.IAuthService;
import learning.more.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService extends ServiceImpl<UserMapper, User> implements IAuthService {


    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder bcryptPasswordEncoder;

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private TenantDao tenantDao;

    @Resource
    private UserRoleRelationDao userRoleRelationDao;

    @Override
    public Map<String,String> authorize(String username, String password){
        log.info("开始验证----");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        // 进行账号密码校验
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
        chaim.put("roleCodeList", user.getRoleCode());
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

    @Override
    public PageItem<List<UserVO>> listPage(Integer pageNum, Integer pageSize, String name, String tenantName, String roleName) {
        Page<User> page = new Page<>(pageNum, pageSize);
        Page<User> userPage = userDao.getBaseMapper().selectPage(page, new LambdaQueryWrapper<User>()
                .eq(User::getIsDeleted, 0)
                .like(!StringUtils.isBlank(name), User::getName, name));
        List<UserRoleRelation> userRoleRelations = userRoleRelationDao.lambdaQuery()
                .select(UserRoleRelation::getRoleId, UserRoleRelation::getUserId)
                .in(UserRoleRelation::getUserId, userPage.getRecords().stream().map(User::getId).toArray()).list();
        Map<Integer, List<Long>> userIdToRoleIdMap = userRoleRelations.stream().collect(Collectors.groupingBy(
                UserRoleRelation::getUserId,                             // 分组 key：userId
                Collectors.mapping(UserRoleRelation::getRoleId, Collectors.toList()) // 分组后 value：List<roleId>
        ));
        List<Long> roleIds = userRoleRelations.stream().map(UserRoleRelation::getRoleId).toList();
        List<Role> roleList = roleDao.lambdaQuery()
                .select(Role::getRoleName, Role::getId)
                .eq(Role::getActive, 0)
                .in(!roleIds.isEmpty(), Role::getId, roleIds)
                .like(!StringUtils.isBlank(roleName), Role::getRoleName, roleName).list();
        List<Long> tenantIdList = userPage.getRecords().stream().map(User::getTenantId).toList();
        List<Tenant> tenantList = tenantDao.lambdaQuery()
                .select(Tenant::getId, Tenant::getName)
                .eq(Tenant::getIsDeleted, 0)
                .in(!tenantIdList.isEmpty(), Tenant::getId, tenantIdList)
                .like(!StringUtils.isBlank(tenantName), Tenant::getName, tenantName).list();
        Map<Long, String> roleIdWithNameMap = roleList.stream().collect(Collectors.toMap(Role::getId, Role::getRoleName));
        Map<Long, String> tenantIdWithNameMap = tenantList.stream().collect(Collectors.toMap(Tenant::getId, Tenant::getName));
        List<UserVO> userVOList = new ArrayList<>();
        for (User userPageRecord : userPage.getRecords()) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userPageRecord, userVO);
            List<String> roleNameList = new ArrayList<>();
            List<Long> roleIdList = userIdToRoleIdMap.get(userPageRecord.getId());
            if (roleIdList != null) {
                roleIdList.forEach(roleId -> {
                    roleNameList.add(roleIdWithNameMap.get(roleId));
                });
                userVO.setRoleNameList(roleNameList);
            }
            userVO.setTenantName(tenantIdWithNameMap.get(userPageRecord.getTenantId()));
            userVOList.add(userVO);
        }
        return new PageItem<>(userPage.getTotal(), userPage.getPages(), pageNum, userVOList);
    }
}
