package com.li.shiro;

import com.github.pagehelper.util.StringUtil;
import com.li.bean.Permission;
import com.li.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author licheng
 * @description shiro页面访问权限控制
 * @create 2018/11/14 22:47
 */
public class FilterChainDefinitionMapBuilder {
    private final Logger logger = LoggerFactory.getLogger(FilterChainDefinitionMapBuilder.class);
    @Autowired
    private PermissionService permissionService;

    /**
     * 初始化页面访问权限
     *
     * @param
     * @return java.util.Map<java.lang.String       ,       java.lang.String>
     */
    public Map<String, String> buildFilterChainDefinitionMap() {
        //从数据库获取权限信息,动态配置页面访问权限
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/lib/**","anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        //已经登录，或者记住我登录
        filterChainDefinitionMap.put("/home", "user");
        List<Permission> permissionList = permissionService.getAllPermission();
        logger.info("共有{}组权限信息", permissionList.size());
        permissionList.forEach(permission -> {
            if (StringUtil.isNotEmpty(permission.getPCode())) {
                String permissionInfo = "perms[" + permission.getPCode() + "]";
                filterChainDefinitionMap.put(permission.getPUrl(), permissionInfo);
            }
        });
        filterChainDefinitionMap.put("/**", "authc");
        logger.info("当前权限信息{}",filterChainDefinitionMap);
        return filterChainDefinitionMap;
    }
}
