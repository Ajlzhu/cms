package com.li.controller;

import com.github.pagehelper.PageInfo;
import com.li.bean.ResponseResult;
import com.li.bean.User;
import com.li.service.UserService;
import com.li.utils.ResponseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @description 用户管理
 * @create 2018/11/12 16:24
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${DEFAULT_PASSWORD}")
    private String DEFAULT_PASSWORD;
    @Autowired
    private UserService userService;

    /**
     * 保存用户信息
     * @param session
     * @param user
     * @param roleIds 用户角色id(多个之间逗号分隔)
     * @return com.li.bean.ResponseResult
     */

    @PostMapping("/save")
    public ResponseResult saveUser(HttpSession session,User user,String roleIds){
        String message = "保存成功";
        User creator = (User) session.getAttribute("user");
        user.setPassword(DEFAULT_PASSWORD);
        user.setCreator(creator);
        boolean b = userService.saveUser(user, roleIds);
        message = b ? message : "保存失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id修改用户信息
     * @param user
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/update/id")
    public ResponseResult updateUserById(User user,HttpSession session){
        String message = "修改成功";
        User modifier = (User) session.getAttribute("user");
        user.setModifier(modifier);
        boolean b = userService.updateUserById(user);
        message = b ? message : "修改失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 修改用户密码
     * @param oldPassword 原密码
     * @param password 新密码
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/update/password")
    public ResponseResult updatePasswordById(String oldPassword,String password,HttpSession session){
        String message = "修改成功";
        User user = (User) session.getAttribute("user");
        user.setPassword(password);
        boolean b = userService.updatePasswordById(oldPassword, user);
        message = b ? message : "修改失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id删除用户
     * @param id
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/delete/id")
    public ResponseResult deleteUserById(Integer id){
        String message = "删除成功";
        boolean b = userService.deleteUserById(id);
        message = b ? message : "删除失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 批量删除用户
     * @param id","分隔
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/delete/ids")
    public ResponseResult deleteUserById(String id){
        String message = "删除成功";
        String[] ids = id.split(",");
        boolean b = userService.deleteUserByIds(ids);
        message = b ? message : "删除失败";
        return ResponseResultUtil.genResult(message);
    }
    /**
     * 根据id获取用户信息
     * @param id 用户id
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/user/id")
    public ResponseResult getUserById(Integer id){
        User user = userService.getUserById(id);
        return ResponseResultUtil.genResult("查询成功", user);
    }
    /**
     * 根据id获取用户信息
     * @param username 用户名
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/user/username")
    public ResponseResult getUserByUsername(String username){
        User user = userService.getUserByUsername(username);
        return ResponseResultUtil.genResult("查询成功", user);
    }
    /**
     * 根据条件获取用户信息
     * @param pageNum  当前页
     * @param pageSize 每页显示的条数
     * @param user 当前用户
     * @return com.li.bean.ResponseResult
     */
    @PostMapping("/list")
    public Map<String,Object> getUserListByCondition(@RequestParam(value="pageNum",required = true,defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value="pageSize",required = false,defaultValue = "10")Integer pageSize, User user){
        PageInfo<User> pageInfo = userService.getUserListByCondition(pageNum, pageSize, user);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "获取用户信息成功");
        map.put("count", pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
}
