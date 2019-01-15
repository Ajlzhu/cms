package com.li.utils;

import com.li.shiro.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @author licheng
 * @description
 * @create 2018/12/14 16:59
 */
public class shiroUtil {

    /**
     * 清除授权缓存信息
     */
    public static void clearCacheAuthorizationInfo(){
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm myRealm = (ShiroRealm) securityManager.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        myRealm.clearCachedAuthorizationInfo(subject.getPrincipals());
    }
}
