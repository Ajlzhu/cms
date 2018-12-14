package com.li.mapper;

import com.li.bean.RolePermission;

/**
 * @author licheng
 * @description
 * @create 2018/11/13 15:10
 */
public interface RolePermissionMapper {

    boolean saveRolePermission(RolePermission rolePermission);

    boolean updateRolePermissionById(RolePermission rolePermission);

    boolean updateRolePermissionByRoleId(RolePermission rolePermission);

    boolean deleteRolePermissionById(Integer id);

    boolean deleteRolePermissionByRoleId(Integer roleId);
}
