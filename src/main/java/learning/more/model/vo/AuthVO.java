package learning.more.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/4/9
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthVO implements Serializable {

    /**
     * 提示信息
     */
    private String msg;

    /**
     * token
     */
    private String token;

    public AuthVO(String msg) {
        this.msg = msg;
    }
}
