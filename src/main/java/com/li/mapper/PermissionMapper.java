package com.li.mapper;

import com.li.bean.Permission;
import com.li.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/12 16:56
 */
public interface PermissionMapper {

    boolean savePermission(Permission permisssion);

    boolean updatePermissionById(Permission permisssion);

    boolean deletePermissionById(Integer id);

    List<Permission> getAllPermission();

    List<Permission> getPermissionByUserId(@Param("userId") Integer userId, @Param("type") Integer type);

    List<Permission> getPermissionByRoleId(@Param("roleId") Integer roleId, @Param("type") Integer type);
}
