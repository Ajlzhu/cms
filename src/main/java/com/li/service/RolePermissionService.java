package com.li.service;

import com.li.bean.RolePermission;

/**
 * @author licheng
 * @description
 * @create 2018/11/13 17:10
 */
public interface RolePermissionService {

    boolean saveRolePermission(RolePermission rolePermission);

    boolean updateRolePermissionById(RolePermission rolePermission);

    boolean updateRolePermissionByRoleId(RolePermission rolePermission,String permissions);

    boolean deleteRolePermissionById(Integer id);
}
