package com.li.controller;

import com.li.bean.ResponseResult;
import com.li.bean.RolePermission;
import com.li.bean.User;
import com.li.service.RolePermissionService;
import com.li.utils.ResponseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author licheng
 * @description
 * @create 2018/11/13 17:16
 */
@RestController
@RequestMapping("/rolepermission")
public class RolePermissionController {
    private static final Logger logger = LoggerFactory.getLogger(RolePermissionController.class);

    @Autowired
    private RolePermissionService rolePermissionService;
    /**
     * 保存角色权限信息
     * @param rolePermission
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/save")
    public ResponseResult saveRolePermission(RolePermission rolePermission) {
        String message = "保存成功";
        boolean b = rolePermissionService.saveRolePermission(rolePermission);
        message = b ? message : "保存失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id修改角色权限
     * @param rolePermission
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/delete/id")
    public ResponseResult updateRolePermissionById(RolePermission rolePermission) {
        String message = "修改成功";
        boolean b = rolePermissionService.updateRolePermissionById(rolePermission);
        message = b ? message : "修改失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据角色id修改角色权限
     * @param rolePermission
     * @param permissions 权限id(多个id逗号分隔)
     * @param session
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/update/roleId")
    public ResponseResult updateRolePermissionByRoleId(RolePermission rolePermission, String permissions, HttpSession session) {
        String message = "更新角色权限成功";
        User user = (User) session.getAttribute("user");
        rolePermission.setCreator(user);
        boolean b = rolePermissionService.updateRolePermissionByRoleId(rolePermission,permissions);
        message = b ? message : "更新角色权限失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id删除角色权限
     * @param id
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/delete")
    public ResponseResult deleteRolePermissionById(Integer id){
        String message = "删除成功";
        boolean b = rolePermissionService.deleteRolePermissionById(id);
        message = b ? message : "删除失败";
        return ResponseResultUtil.genResult(message);
    }
}
