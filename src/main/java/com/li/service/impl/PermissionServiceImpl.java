package com.li.service.impl;

import com.li.bean.Permission;
import com.li.bean.Role;
import com.li.mapper.PermissionMapper;
import com.li.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/12 17:30
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 保存权限信息
     * @param permisssion
     * @return boolean
     */
    @Override
    public boolean savePermission(Permission permisssion) {
        return permissionMapper.savePermission(permisssion);
    }

    /**
     * 根据权限id修改权限信息
     * @param permisssion
     * @return boolean
     */
    @Override
    public boolean updatePermissionById(Permission permisssion) {
        return permissionMapper.updatePermissionById(permisssion);
    }

    /**
     * 根据权限id删除权限信息
     * @param id
     * @return boolean
     */
    @Override
    public boolean deletePermissionById(Integer id) {
        return permissionMapper.deletePermissionById(id);
    }

    /**
     * 获取所有权限信息
     * @param
     * @return java.util.List<com.li.bean.Permission>
     */
    @Override
    public List<Permission> getAllPermission() {
        return permissionMapper.getAllPermission();
    }
    /**
     * 根据用户id获取用户所有权限
     * @param userId
     * @param type
     * @return java.util.List<com.li.bean.Permission>
     */
    @Override
    public List<Permission> getPermissionByUserId(Integer userId, Integer type) {
        logger.info("获取用户所有权限信息");
        return permissionMapper.getPermissionByUserId(userId,type);
    }
    /**
     * 根据用户id获取用户所有权限
     * @param roleId
     * @param type
     * @return java.util.List<com.li.bean.Permission>
     */
    @Override
    public List<Permission> getPermissionByRoleId(Integer roleId, Integer type) {
        logger.info("获取用户所有权限信息");
        return permissionMapper.getPermissionByRoleId(roleId,type);
    }
}
