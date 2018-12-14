package com.li.service.impl;

import com.li.bean.RolePermission;
import com.li.mapper.RolePermissionMapper;
import com.li.service.RolePermissionService;
import com.li.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author licheng
 * @description
 * @create 2018/11/13 17:11
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    private static final Logger logger = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 保存角色权限
     * @param rolePermission
     * @return boolean
     */
    @Override
    public boolean saveRolePermission(RolePermission rolePermission) {
        return rolePermissionMapper.saveRolePermission(rolePermission);
    }
    /**
     * 根据id修改角色权限
     * @param rolePermission
     * @return boolean
     */
    @Override
    public boolean updateRolePermissionById(RolePermission rolePermission) {
        return rolePermissionMapper.updateRolePermissionById(rolePermission);
    }
    /**
     * 根据角色id修改角色权限
     * @param rolePermission
     * @param permissions 权限id(多个id逗号分隔)
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRolePermissionByRoleId(RolePermission rolePermission,String permissions) {
        //1.根据角色id删除角色对应的权限信息
        boolean b = rolePermissionMapper.deleteRolePermissionByRoleId(rolePermission.getRoleId());
        //2.重新保存角色对应的权限信息
        String[] permissionId = permissions.split(",");
        for (int i = 0; i < permissionId.length; i++){
            rolePermission.setPermissionId(Integer.parseInt(permissionId[i]));
            rolePermission.setCreateTime(DateUtil.date2Str(new Date()));
            rolePermissionMapper.saveRolePermission(rolePermission);
        }
        //3.更新shiro授权缓存
        return b;
    }
    /**
     * 根据id删除角色权限
     * @param id
     * @return boolean
     */
    @Override
    public boolean deleteRolePermissionById(Integer id) {
        return rolePermissionMapper.deleteRolePermissionById(id);
    }
}
