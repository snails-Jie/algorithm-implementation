package zhangjie.api.design.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import zhangjie.api.design.enums.ResultCode;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description: 响应体
 * @author: zhangjie
 * @Date: 2020/10/15 10:24
 **/
@Data
@NoArgsConstructor
public class Result implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public Result(ResultCode resultCode,Object data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    /** 返回成功 */
    public static Result success(){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /** 返回成功 */
    public static Result success(Object data){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /** 返回失败 */
    public static Result failure(ResultCode resultCode){
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    /** 返回失败 */
    public static Result failure(ResultCode resultCode,Object data){
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
}
