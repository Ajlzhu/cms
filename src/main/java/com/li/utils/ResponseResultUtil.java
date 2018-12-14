package com.li.utils;

import com.li.bean.ResponseResult;

/**
 * @author licheng
 * @description 统一响应工具类
 * @create 2018/11/11 13:14
 */
public class ResponseResultUtil<T> {
    /**
     * 生成响应成功(不带正文)的结果
     * @param message
     * @return com.li.bean.ResponseResult<T>
     */
    public static ResponseResult genResult(String message) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        responseResult.setMessage(message);
        return responseResult;
    }
    /**
     * 生成响应成功(带正文)的结果
     * @param message 成功提示信息
     * @param data 正文信息
     * @return com.li.bean.ResponseResult<T>
     */
    public static<T> ResponseResult<T> genResult(String message , T data) {
        ResponseResult<T> responseResult = new ResponseResult<T>();
        responseResult.setSuccess(true);
        responseResult.setMessage(message);
        responseResult.setData(data);
        return responseResult;
    }
    /**
     * 生成响应失败(不带正文)的结果
     * @param message 成功提示信息
     * @return com.li.bean.ResponseResult<T>
     */
    public static ResponseResult genErrorResult(String message) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(false);
        responseResult.setMessage(message);
        return responseResult;
    }
    /**
     * 生成响应失败(带正文)的结果
     * @param message 成功提示信息
     * @param data 正文信息
     * @return com.li.bean.ResponseResult<T>
     */
    public static<T> ResponseResult<T> genErrorResult(String message , T data) {
        ResponseResult<T> responseResult = new ResponseResult<T>();
        responseResult.setSuccess(false);
        responseResult.setMessage(message);
        responseResult.setData(data);
        return responseResult;
    }
}
