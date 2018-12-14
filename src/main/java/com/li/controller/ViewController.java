package com.li.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author licheng
 * @description 功能页面跳转controller
 * @create 2018/11/20 14:37
 */
@Controller
public class ViewController {

    /**
     * 跳转到新增用户页面
     * @return java.lang.String
     */
    @GetMapping("/user/add")
    public String addUser(){
        return "/user/add";
    }
    /**
     * 跳转到修改用户页面
     * @return java.lang.String
     */
    @GetMapping("/user/edit")
    public String editUser(){
        return "/user/edit";
    }
    /**
     * 跳转到新增权限页面
     * @return java.lang.String
     */
    @GetMapping("/permission/add")
    public String addPermission(){
        return "/permission/add";
    }
    /**
     * 跳转到修改权限页面
     * @return java.lang.String
     */
    @GetMapping("/permission/edit")
    public String editPermission(){
        return "/permission/edit";
    }
    /**
     * 跳转到新增角色页面
     * @return java.lang.String
     */
    @GetMapping("/role/add")
    public String addRole(){
        return "/role/add";
    }
    /**
     * 跳转到修改角色页面
     * @return java.lang.String
     */
    @GetMapping("/role/edit")
    public String editRole(){
        return "/role/edit";
    }
}
