package com.li.utils;

import com.li.bean.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author licheng
 * @description 密码工具
 * @create 2018/11/14 14:05
 */
public class PasswordHelper {
    private static final String algorithmName = "md5";
    private static final int hashIterations = 2;

    public static void encryptPassword(User user) {
        String newPassword = new SimpleHash(algorithmName, user.getPassword(),  ByteSource.Util.bytes(user.getUsername()), hashIterations).toString();
        user.setPassword(newPassword);
    }
    public static void main(String[] args) {
        //PasswordHelper passwordHelper = new PasswordHelper();
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        PasswordHelper.encryptPassword(user);
        System.out.println(user);
    }
}
