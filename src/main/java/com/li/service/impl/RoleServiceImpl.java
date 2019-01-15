package com.li.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.li.bean.Role;
import com.li.bean.RolePermission;
import com.li.mapper.RoleMapper;
import com.li.mapper.RolePermissionMapper;
import com.li.service.RoleService;
import com.li.utils.DateUtil;
import com.li.utils.shiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/12 22:57
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    /**
     * 保存角色
     * @param role
     * @return boolean
     */
    @Override
    public boolean saveRole(Role role) {
        return roleMapper.saveRole(role);
    }

    /**
     * 根据角色id修改角色信息
     * @param role
     * @param rolePermission
     * @param pIds
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRoleById(Role role, RolePermission rolePermission, String pIds) {
        Date date = new Date();
        String time = DateUtil.date2Str(date);
        //1.更新角色信息
        role.setUpdateTime(time);
        boolean b = roleMapper.updateRoleById(role);
        //2.根据角色id删除角色对应的权限信息
        rolePermissionMapper.deleteRolePermissionByRoleId(rolePermission.getRoleId());
        //3.重新保存角色对应的权限信息
        String[] permissionId = pIds.split(",");
        for (int i = 0; i < permissionId.length; i++){
            rolePermission.setPermissionId(Integer.parseInt(permissionId[i]));
            rolePermission.setCreateTime(time);
            rolePermissionMapper.saveRolePermission(rolePermission);
        }
        //4.更新shiro授权缓存
        shiroUtil.clearCacheAuthorizationInfo();
        return b;
    }

    @Override
    public boolean deleteRoleById(Integer id) {
        return roleMapper.deleteRoleById(id);
    }

    @Override
    public boolean deleteRoleByIds(Integer[] ids) {
        return roleMapper.deleteRoleByIds(ids);
    }

    @Override
    public List<Role> getRoleListByUserId(Integer userId) {
        return roleMapper.getRoleListByUserId(userId);
    }
    @Override
    public PageInfo<Role> getRoleListByCondition(Integer pageNum, Integer pageSize, Role role) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roleList = roleMapper.getRoleListByCondition(role);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        return pageInfo;
    }
    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = roleMapper.getRoleListByCondition(null);
        return roleList;
    }
}
