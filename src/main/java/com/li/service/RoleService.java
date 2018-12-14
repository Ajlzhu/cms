package com.li.service;

import com.github.pagehelper.PageInfo;
import com.li.bean.Role;
import com.li.bean.RolePermission;
import com.li.bean.User;

import java.util.List;

/**
 * @author licheng
 * @description 角色管理
 * @create 2018/11/12 22:56
 */
public interface RoleService {

    boolean saveRole(Role role);

    boolean updateRoleById(Role role,RolePermission rolePermission,String pIds);

    boolean deleteRoleById(Integer id);

    boolean deleteRoleByIds(Integer[] ids);

    List<Role> getRoleListByUserId(Integer userId);

    PageInfo<Role> getRoleListByCondition(Integer pageNum, Integer pageSize, Role role);

    List<Role> getRoleList();
}
