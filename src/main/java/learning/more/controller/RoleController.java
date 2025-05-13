package learning.more.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import learning.more.dao.RoleDao;
import learning.more.model.domain.Role;
import learning.more.model.domain.School;
import learning.more.model.vo.PageItem;
import learning.more.model.vo.SchoolOverviewVO;
import learning.more.model.vo.SuccessVO;
import learning.more.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/5/11
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@Slf4j
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleDao roleDao;

    @GetMapping("/listPage")
    public PageItem<List<Role>> listPage(@RequestParam("pageNum") @Parameter(description = "页码") Integer pageNum,
                                         @RequestParam("pageSize") @Parameter(description = "每页数量") Integer pageSize,
                                         @RequestParam(value = "roleName", required = false) String roleName,
                                         @RequestParam(value = "roleCode", required = false) String roleCode) {
        Page<Role> pageObj = new Page<>(pageNum, pageSize);
        Page<Role> rolePage = roleDao.getBaseMapper().selectPage(pageObj, new LambdaQueryWrapper<Role>()
                .eq(Role::getActive, 0)
                .like(!StringUtil.isNullAndEmpty(roleName), Role::getRoleName, roleName)
                .like(!StringUtil.isNullAndEmpty(roleCode), Role::getRoleCode, roleCode));
        return new PageItem<>(rolePage.getTotal(), rolePage.getPages(), pageNum, rolePage.getRecords());
    }

    @PostMapping("/createRole")
    public SuccessVO<Role> createRole(@RequestBody Role role) {
        roleDao.save(role);
        return SuccessVO.successWithData(role);
    }

    @PutMapping("/updateRole")
    public SuccessVO<Role> updateRole(@RequestBody Role role) {
        roleDao.updateById(role);
        return SuccessVO.successWithData(role);
    }

    @DeleteMapping("/deleteRole")
    public SuccessVO<Role> deleteRole(@RequestParam("roleId") Long roleId) {
        Role role = roleDao.getById(roleId);
        roleDao.removeById(roleId);
        return SuccessVO.successWithData(role);
    }
}
