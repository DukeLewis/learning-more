package learning.more.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

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

    public AuthUserEntity(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer id, Long tenantId) {
        super(username, password, authorities);
        this.id = id;
        this.tenantId = tenantId;
    }

    public Integer getId() {
        return id;
    }

    public Long getTenantId() {
        return tenantId;
    }
}
