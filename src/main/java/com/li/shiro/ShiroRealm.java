package com.li.shiro;

import com.github.pagehelper.util.StringUtil;
import com.li.bean.Permission;
import com.li.bean.User;
import com.li.service.PermissionService;
import com.li.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author licheng
 * @description 自定义realm
 * @create 2018/11/13 20:38
 */
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /**
         * 取到的是认证方法放入的principal(这里认证方法放入的是user对象)
         * 若有多个realm的认证方法则取到的是第一个认证方法放入的principal
         */
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Permission> permissionList = permissionService.getPermissionByUserId(user.getUserId(), null);
        //logger.info("用户信息{}, 用户权限集合{}", user, permissionList);
        //将获取到的当前用户权限信息存入shiro授权对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        permissionList.forEach(permission -> {
            if (StringUtil.isNotEmpty(permission.getPCode())) {
                info.addStringPermission(permission.getPCode());
            }
        });

        return info;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String username = (String) authenticationToken.getPrincipal();
        //根据用户名获取用户信息
        User user = userService.getUserByUsername(username);
        if (user == null) {
            //未知账号
            throw new UnknownAccountException();
        } else if (user.getStatus() == 0) {
            //账号已锁定
            throw new LockedAccountException();
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(username), this.getName());
        return info;
    }
    /**
     * 重新加载 用户授权信息
     * @return void
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
}
