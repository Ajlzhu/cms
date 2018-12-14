package com.li.controller;

import com.li.bean.User;
import com.li.service.PermissionService;
import com.li.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author licheng
 * @description 主页面跳转controller
 * @create 2018/11/9 3:33
 */

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转登录页面
     * @param
     * @return java.lang.String
     */
    @GetMapping({"/","/login"})
    public String login(){
        return "login";
    }
    /**
     * 登录方法登录成功跳转到主页面
     * @param user
     * @return org.springframework.web.servlet.ModelAndView
     */
    @PostMapping("/login")
    public String login(HttpSession session,User user) throws AuthenticationException {
        Subject subject = SecurityUtils.getSubject();
        //若当前用户未认证(登录)
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            //记住我功能
            //token.setRememberMe(true);
            subject.login(token);
            User userInfo = (User)subject.getPrincipal();
            session.setAttribute("user",userInfo);
        }
        return "redirect:/home";
    }
    /**
     * 跳转注册页面
     * @param
     * @return java.lang.String
     */
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    /**
     * 注册成功跳转到登录页面
     * @param user
     * @param roleIds 用户角色id(多个角色id逗号分隔)
     * @return java.lang.String
     */
    @PostMapping("/register")
    public String register(User user,String roleIds){
        userService.saveUser(user,roleIds);
        return "login";
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/user")
    public String user(){
        return "user/list";
    }
    @GetMapping("/role")
    public String role(){
        return "role/list";
    }
    @GetMapping("changepwd")
    public String changePwd(){
        return "user/changepwd";
    }
    @GetMapping("/permission")
    public String permission(){
        return "permission/list";
    }
    @GetMapping("/workbench")
    public String workbench(){
        return "workbench";
    }
    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

}
