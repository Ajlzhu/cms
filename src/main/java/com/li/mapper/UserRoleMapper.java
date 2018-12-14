package com.li.mapper;

import com.li.bean.UserRole;

import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/13 13:45
 */
public interface UserRoleMapper {

    boolean saveUserRole(UserRole userRole);

    boolean updateUserRoleById(UserRole userRole);

    boolean updateUserRoleByUserId(UserRole userRole);

    boolean deleteUserRoleById(Integer id);

    List<UserRole> getUserRoleByCondition(UserRole userRole);

}
