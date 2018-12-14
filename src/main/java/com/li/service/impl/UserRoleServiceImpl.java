package com.li.service.impl;

import com.li.bean.UserRole;
import com.li.mapper.UserRoleMapper;
import com.li.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author licheng
 * @description
 * @create 2018/11/13 14:47
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleMapper userRoleMapper;
    /**
     * 保存用户角色
     * @param userRole
     * @return boolean
     */
    @Override
    public boolean saveUserRole(UserRole userRole) {
        return userRoleMapper.saveUserRole(userRole);
    }
    /**
     * 根据id修改用户角色
     * @param userRole
     * @return boolean
     */
    @Override
    public boolean updateUserRoleById(UserRole userRole) {
        return userRoleMapper.updateUserRoleById(userRole);
    }
    /**
     * 根据用户id修改用户角色
     * @param userRole
     * @return boolean
     */
    @Override
    public boolean updateUserRoleByUserId(UserRole userRole) {
        return userRoleMapper.updateUserRoleByUserId(userRole);
    }

    /**
     * 根据id删除用户角色
     * @param id
     * @return boolean
     */
    @Override
    public boolean deleteUserRoleById(Integer id) {
        return userRoleMapper.deleteUserRoleById(id);
    }
}
