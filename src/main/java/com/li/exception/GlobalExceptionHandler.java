package com.li.exception;

import com.li.bean.ResponseResult;
import com.li.utils.ResponseResultUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author licheng
 * @description
 * @create 2018/11/11 13:58
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseResult handleException(Exception e){
        logger.error(e.getMessage(), e);

        return  ResponseResultUtil.genErrorResult("操作失败");
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResponseResult handleBusinessException(BusinessException e){
        logger.error(e.getMessage(), e);
        return ResponseResultUtil.genErrorResult(e.getMessage());
    }
    /**
     * 处理登录认证异常
     * @param e
     * @return org.springframework.web.servlet.ModelAndView
     */
    @ExceptionHandler(AuthenticationException.class)
    public ModelAndView handleAuthenticationException(AuthenticationException e){
        ModelAndView mav = new ModelAndView();
        String message = "用户名或密码错误";
        logger.error("用户认证异常",e);
        if (e instanceof UnknownAccountException){
            logger.error("账号不存在",e);
        } else if (e instanceof LockedAccountException){
            logger.error("账号已锁定",e);
            message = "账号已锁定，请联系管理员";
        } else {
            logger.error("用户名或密码错误",e);
        }
        mav.setViewName("login");
        mav.addObject("message",message);
        return mav;
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseResultUtil.genErrorResult(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

}
