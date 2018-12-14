package com.li.utils;

import com.li.bean.Permission;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author licheng
 * @description 生成权限树(菜单树)
 * @create 2018/11/17 14:47
 */
public class PermissionTreeUtil {

    /**
     * 生成当前权限的权限树方法
     * @param permission  当前权限
     * @param permissionList   所有权限
     * @return void
     */

    public static Permission genPermissionTree(Permission permission ,List<Permission> permissionList){
        //获取当前权限的子权限集合
        List<Permission> childList = permissionList.stream().filter(p->permission.getPId().equals(p.getParentId())).collect(Collectors.toList());
        //递归设置子权限
        if (!childList.isEmpty()){
            childList.forEach(p ->{
                genPermissionTree(p,permissionList);
            });
        }
        //设置当前权限的子权限集合
        permission.setChildPermission(childList);
        return permission;
    }
}
