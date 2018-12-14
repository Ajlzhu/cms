package com.li.controller;

import com.github.pagehelper.PageInfo;
import com.li.bean.ResponseResult;
import com.li.bean.Role;
import com.li.bean.RolePermission;
import com.li.bean.User;
import com.li.service.RoleService;
import com.li.utils.ResponseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author licheng
 * @description
 * @create 2018/11/12 23:00
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 新增角色信息
     * @param role
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/save")
    public ResponseResult saveRole(Role role){
        String message = "保存成功";
        boolean b = roleService.saveRole(role);
        message = b ? message : "保存失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id修改角色
     * @param role
     * @param rolePermission 角色权限
     * @param pIds 权限id（多个id逗号分隔）
     * @param session
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/update/id")
    public ResponseResult updateRoleById(Role role, RolePermission rolePermission,
                                         String pIds,HttpSession session){
        String message = "修改成功";
        User user = (User) session.getAttribute("user");
        role.setModifier(user);
        rolePermission.setCreator(user);
        boolean b = roleService.updateRoleById(role,rolePermission,pIds);
        message = b ? message : "修改失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id删除角色
     * @param id
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/delete/id")
    public ResponseResult deleteRoleById(Integer id){
        String message = "删除成功";
        boolean b = roleService.deleteRoleById(id);
        message = b ? message : "删除失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据ids批量删除角色
     * @param ids
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/delete/ids")
    public ResponseResult deleteRoleByIds(Integer[] ids){
        String message = "删除成功";
        boolean b = roleService.deleteRoleByIds(ids);
        message = b ? message : "删除失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据条件获取角色列表
     * @param pageNum
     * @param pageSize
     * @param role
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @PostMapping("/list")
    public Map<String, Object> getRoleListByCondition(@RequestParam(value="pageNum",required = false,defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value="pageSize",required = false,defaultValue = "10")Integer pageSize, Role role){
        PageInfo<Role> pageInfo = roleService.getRoleListByCondition(pageNum, pageSize, role);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "获取角色信息成功");
        map.put("count", pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    /**
     * 获取所有角色列表
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @PostMapping("/list/all")
    public ResponseResult getRoleList(){
        List<Role> roleList = roleService.getRoleList();
        return ResponseResultUtil.genResult("获取用户角色成功", roleList);
    }
    /**
     * 根据用户id获取用户角色列表
     * @param userId
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/list/userId")
    public ResponseResult getRoleListByUserId(Integer userId){
        List<Role> roleList = roleService.getRoleListByUserId(userId);
        return ResponseResultUtil.genResult("获取用户角色成功", roleList);
    }
}
