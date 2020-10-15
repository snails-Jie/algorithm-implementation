package zhangjie.api.design.enums;

/**
 * 错误类型归类到某个区间内
 * 1. #1000～1999 区间表示参数错误
 * 2. #2000～2999 区间表示用户错误
 * 3. #3000～3999 区间表示接口异常
 * 状态码枚举
 * @author zhangjie
 */
public enum ResultCode {
    /** 成功状态码 */
    SUCCESS(1,"成功"),
    /** 1000～1999 区间表示参数错误 */
    PARAM_IS_INVALID(1001,"参数无效"),
    /** 2000～2999 区间表示用户错误  */
    USER_NOT_LOGGED_IN(2001,"用户未登录"),
    /** 3000～3999 区间表示系统错误  */
    SYS_ERROR(3001,"系统错误");


    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }



}
