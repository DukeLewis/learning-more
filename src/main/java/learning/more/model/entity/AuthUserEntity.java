package learning.more.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 *  用户校验实体类对象
 * @author ASUS
 */
public class AuthUserEntity extends User {

    /** 用户id */
    private Integer id;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 角色编码
     */
    private List<String> roleCode;

    public AuthUserEntity(String username, String password, Collection<? extends GrantedAuthority> authorities,
                          Integer id, Long tenantId, List<String> roleCode) {
        super(username, password, authorities);
        this.id = id;
        this.tenantId = tenantId;
        this.roleCode = roleCode;
    }

    public Integer getId() {
        return id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public List<String> getRoleCode() {
        return roleCode;
    }
}
