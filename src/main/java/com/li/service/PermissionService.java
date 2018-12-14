package com.li.service;

import com.li.bean.Permission;
import com.li.bean.Role;

import java.util.List;

/**
 * @author licheng
 * @description 权限接口
 * @create 2018/11/12 17:29
 */
public interface PermissionService {

    boolean savePermission(Permission permisssion);

    boolean updatePermissionById(Permission permisssion);

    boolean deletePermissionById(Integer id);

    List<Permission> getAllPermission();

    List<Permission> getPermissionByUserId(Integer userId, Integer type);

    List<Permission> getPermissionByRoleId(Integer roleId, Integer type);

}
