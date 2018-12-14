package com.li.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author licheng
 * @description 统一响应结果数据
 * @create 2018/11/11 13:06
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    //错误码
    private boolean success;
    //提示信息
    private String message;
    //响应数据
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    @Override
    public String toString() {
        return "ResponseResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
