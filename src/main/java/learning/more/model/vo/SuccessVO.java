package learning.more.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 操作成功响应对象
 * @author：dukelewis
 * @date: 2025/1/8
 * @Copyright： https://github.com/DukeLewis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "操作成功VO")
public class SuccessVO<T> {
    @Schema(description = "操作是否成功")
    private Boolean success;

    @Schema(description = "返回数据")
    private T data;

    /**
     * 操作成功，返回空数据
     * @return SuccessVO
     */
    public static SuccessVO successNotData() {
        return new SuccessVO(true, null);
    }

    /**
     * 操作成功，返回数据
     * @param data 数据
     * @return SuccessVO
     * @param <T> 数据类型
     */
    public static <T> SuccessVO successWithData(T data) {
        return new SuccessVO(true, data);
    }
}
