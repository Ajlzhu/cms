package com.li.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.li.bean.User;
import com.li.bean.UserRole;
import com.li.mapper.UserMapper;
import com.li.mapper.UserRoleMapper;
import com.li.service.UserService;
import com.li.utils.DateUtil;
import com.li.utils.PasswordHelper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/12 16:22
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUser(User user,String roleIds) {
        logger.info("保存用户信息");
        Date date = new Date();
        String createTime = DateUtil.date2Str(date);
        //加密方式需要shiro配置文件中保持一致
        PasswordHelper.encryptPassword(user);
        user.setCreateTime(createTime);
        boolean uFlag = userMapper.saveUser(user);
        logger.info("用户id{}",user.getUserId());

        //插入用户角色信息表
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setCreator(user);
        userRole.setCreateTime(createTime);
        String[] ids = roleIds.split(",");
        for (int i = 0; i < ids.length; i++){
            Integer id = Integer.parseInt(ids[i]);
            userRole.setRoleId(id);
            userRoleMapper.saveUserRole(userRole);
        }
        return uFlag;
    }

    @Override
    public boolean updateUserById(User user) {
        Date date = new Date();
        user.setUpdateTime(DateUtil.date2Str(date));
        logger.info("根据id修改用户信息");
        return userMapper.updateUserById(user);
    }

    @Override
    public boolean updatePasswordById(String oldPassword,User user) {
        //获取用户的新密码(controller层中已设置)
        String password = user.getPassword();

        //将用户输入的原密码与数据库中的做对比
        user.setPassword(oldPassword);
        PasswordHelper.encryptPassword(user);
        //根据用户名获取原用户信息
        User oldUser = userMapper.getUserByUsername(user.getUsername());
        logger.info("用户输入的原密码{}",user.getPassword());
        logger.info("数据库中的密码{}",oldUser.getPassword());
        logger.info("新密码{}",password);
        //原密码与数据库中一直则修改用户密码,不一致则返回
        if (user.getPassword().equals(oldUser.getPassword())){
            user.setPassword(password);
            PasswordHelper.encryptPassword(user);
            user.setUpdateTime(DateUtil.date2Str(new Date()));
            user.setModifier(user);
            return userMapper.updateUserById(user);
        }
        return false;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        logger.info("根据id删除用户信息");
        return userMapper.deleteUserById(id);
    }
    @Override
    public boolean deleteUserByIds(String[] ids) {
        logger.info("批量删除用户信息");
        return userMapper.deleteUserByIds(ids);
    }

    @Override
    public User getUserById(Integer id) {
        logger.info("根据id获取用户信息");
        return userMapper.getUserById(id);
    }
    @Override
    public User getUserByUsername(String username) {
        logger.info("根据username获取用户信息");
        return userMapper.getUserByUsername(username);
    }

    @Override
    public PageInfo<User> getUserListByCondition(Integer pageNum,Integer pageSize,User user) {
        logger.info("根据条件获取用户信息");
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.getUserListByCondition(user);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return pageInfo;
    }
}
