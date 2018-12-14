package com.li.controller;

import com.li.bean.ResponseResult;
import com.li.bean.UserRole;
import com.li.service.UserRoleService;
import com.li.utils.ResponseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author licheng
 * @description
 * @create 2018/11/13 15:10
 */
@RestController
@RequestMapping("/userrole")
public class UserRoleController {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 保存用户角色
     * @param userRole
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/save")
    public ResponseResult saveUserRole(UserRole userRole){
        String message = "保存成功";
        boolean b = userRoleService.saveUserRole(userRole);
        message = b ? message : "保存失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id修改用户角色
     * @param userRole
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/update/id")
    public ResponseResult updateUserRoleById(UserRole userRole) {
        String message = "修改成功";
        boolean b = userRoleService.updateUserRoleById(userRole);
        message = b ? message : "修改失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据用户id修改用户角色信息
     * @param userRole
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/update/userid")
    public ResponseResult updateUserRoleByUserId(UserRole userRole) {
        String message = "修改成功";
        boolean b = userRoleService.updateUserRoleByUserId(userRole);
        message = b ? message : "修改失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id删除用户角色
     * @param id
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/delete/id")
    public ResponseResult deleteUserRoleById(Integer id) {
        String message = "删除成功";
        boolean b = userRoleService.deleteUserRoleById(id);
        message = b ? message : "删除失败";
        return ResponseResultUtil.genResult(message);
    }
}
