package com.li.mapper;

import com.li.bean.User;

import java.util.List;

/**
 * @author licheng
 * @description 用户mapper
 * @create 2018/11/12 15:09
 */
public interface UserMapper {

    boolean saveUser(User user);

    boolean updateUserById(User user);

    boolean deleteUserById(Integer id);

    boolean deleteUserByIds(String[] id);

    User getUserById(Integer id);

    User getUserByUsername(String username);

    List<User> getUserListByCondition(User user);


}
