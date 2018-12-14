package com.li.service;

import com.github.pagehelper.PageInfo;
import com.li.bean.User;

import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/12 16:21
 */
public interface UserService {

    boolean saveUser(User user,String roleIds);

    boolean updateUserById(User user);

    boolean updatePasswordById(String oldPassword,User user);

    boolean deleteUserById(Integer id);

    boolean deleteUserByIds(String[] ids);

    User getUserById(Integer id);

    User getUserByUsername(String username);

    PageInfo<User> getUserListByCondition(Integer pageNum,Integer pageSize,User user);
}
