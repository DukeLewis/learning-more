package learning.more.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import learning.more.model.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/5/12
 * @Copyright： https://github.com/DukeLewis
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends User {
    /**
     * 机构名称
     */
    @TableField(exist = false)
    private String tenantName;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    private List<String> roleNameList;

    public UserVO() {
        super();
    }

    public UserVO(String tenantName, List<String> roleNameList) {
        super();
        this.tenantName = tenantName;
        this.roleNameList = roleNameList;
    }
}
