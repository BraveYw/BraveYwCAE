package com.yang.cae.util.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private Integer code = 0;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "Successful_operation";

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private Object data;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public Result() {

    }

    public Result<?> okMake(String message) {
        this.message = message;
        this.code = ResultCode.success_204;
        this.success = true;
        return this;
    }

    public static Result<?> ok() {
        Result<?> r = new Result<>();
        r.setSuccess(true);
        r.setCode(ResultCode.success_204);
        r.setMessage("成功");
        return r;
    }

    public static Result<?> ok(String msg) {
        Result<?> r = new Result<>();
        r.setSuccess(true);
        r.setCode(ResultCode.success_204);
        r.setMessage(msg);
        return r;
    }

    public static Result<?> ok(Object data) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(ResultCode.success_200);
        r.setData(data);
        return r;
    }

    public static Result<?> error(String msg) {
        return error(ResultCode.success_400, msg);
    }

    public static Result<?> error(int code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public Result<?> error500(String message) {
        this.message = message;
        this.code = ResultCode.success_500;
        this.success = false;
        return this;
    }

    /**
     * 无权限访问返回结果
     */
    public static Result<?> noAuth(String msg) {
        return error(ResultCode.success_400, msg);
    }
}
