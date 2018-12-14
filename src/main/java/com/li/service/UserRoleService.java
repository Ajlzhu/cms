package com.li.service;

import com.li.bean.UserRole;

/**
 * @author licheng
 * @description
 * @create 2018/11/13 14:46
 */
public interface UserRoleService {

    boolean saveUserRole(UserRole userRole);

    boolean updateUserRoleById(UserRole userRole);

    boolean updateUserRoleByUserId(UserRole userRole);

    boolean deleteUserRoleById(Integer id);
}
