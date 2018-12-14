package com.li.controller;

import com.li.bean.Permission;
import com.li.bean.ResponseResult;
import com.li.bean.Role;
import com.li.service.PermissionService;
import com.li.service.RoleService;
import com.li.utils.PermissionTreeUtil;
import com.li.utils.ResponseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author licheng
 * @description 权限管理
 * @create 2018/11/12 22:00
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    /**
     * 新增权限信息
     * @param permission
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("save")
    public ResponseResult savePermission(Permission permission){
        String message = "保存成功";
        boolean b = permissionService.savePermission(permission);
        message = b ? message : "保存失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据角色id修改权限信息
     * @param permission
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/update/id")
    public ResponseResult updatePermissionById(Permission permission){
        String message = "修改成功";
        boolean b = permissionService.updatePermissionById(permission);
        message = b ? message : "修改失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id删除权限信息
     * @param id
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/delete/id")
    public ResponseResult deletePermissionById(Integer id){
        String message = "删除成功";
        boolean b = permissionService.deletePermissionById(id);
        message = b ? message : "删除失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     *  获取所有权限信息
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/allpermission")
    public ResponseResult getAllPermission(){
        List<Permission> permissionList = permissionService.getAllPermission();
        return ResponseResultUtil.genResult("获取权限信息成功", permissionList);
    }
    /**
     * 根据userId获取用户权限信息
     * @param userId
     * @param type
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/list")
    public ResponseResult getPermissionByUserId(Integer userId,Integer type){
        List<Permission> userPermissionList = permissionService.getPermissionByUserId(userId, type);
        return ResponseResultUtil.genResult("获取用户权限信息成功", userPermissionList);
    }
    /**
     * 获取当前用的所有菜单 并以树状返回
     * @param userId
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/menu")
    public ResponseResult getUserMenu(Integer userId){
        //菜单树
        List<Permission> menuTree = new ArrayList<>();
        //获取当前用户的所有菜单
        List<Permission> menuList = permissionService.getPermissionByUserId(userId, 1);
        for (Permission menu: menuList){
            if (menu.getParentId() == 0){
                menuTree.add(PermissionTreeUtil.genPermissionTree(menu, menuList));
                continue;
            }
        }
        return ResponseResultUtil.genResult("获取用户菜单成功", menuTree);
    }
    @PostMapping("/tree")
    public ResponseResult renderPermissionTree(Integer roleId){
        //所有权限信息
        List<Permission> allPermissionList = permissionService.getAllPermission();
        //角色拥有的权限信息
        List<Permission> userPermissionList = permissionService.getPermissionByRoleId(roleId, null);
        for(Permission permission : allPermissionList){
            if (userPermissionList.contains(permission)){
                permission.setChecked(true);
            } else {
                permission.setChecked(false);
            }
        }
        return ResponseResultUtil.genResult("获取用户权限树成功", allPermissionList);
    }
}
