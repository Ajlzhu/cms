package com.li.mapper;

import com.li.bean.Role;

import java.util.List;

/**
 * @author licheng
 * @description 角色mapper
 * @create 2018/11/12 22:30
 */
public interface RoleMapper {

    boolean saveRole(Role role);

    boolean updateRoleById(Role role);

    boolean deleteRoleById(Integer id);

    boolean deleteRoleByIds(Integer[] ids);

    List<Role> getRoleListByUserId(Integer userId);

    List<Role> getRoleListByPermissionId(Integer permissionId);

    List<Role> getRoleListByCondition(Role role);
}
