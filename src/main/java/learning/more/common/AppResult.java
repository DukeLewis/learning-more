package learning.more.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import learning.more.common.enums.ResultCode;

@Tag(name = "统一返回对象")
public class AppResult<T> {
    //状态码

    /**
     * 无论是否为空都必须序列化
     */
    @Schema(description = "状态码")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private int code;

    /**
     * 描述信息
     */
    @Schema(description = "描述信息")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String message;

    /**
     * 具体数据
     */
    @Schema(description = "具体数据")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private T data;

    public AppResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AppResult(int code, String message){
        this(code,message,null);
    }

    //成功

    public static AppResult success () {
        return new AppResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    public static AppResult success (String message) {
        return new AppResult(ResultCode.SUCCESS.getCode(), message);
    }

    public static <T> AppResult<T> success (T data) {
        return new AppResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> AppResult<T> success (String message, T data) {
        return new AppResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    //失败

    public static AppResult failed () {
        return new AppResult(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage());
    }

    public static AppResult failed (String message) {
        return new AppResult(ResultCode.FAILED.getCode(), message);
    }

    public static AppResult failed (ResultCode resultCode) {
        return new AppResult(resultCode.getCode(), resultCode.getMessage());
    }







    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
