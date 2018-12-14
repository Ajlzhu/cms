package com.li.bean;

import lombok.*;

/**
 * @author licheng
 * @description 角色权限
 * @create 2018/11/12 14:57
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RolePermission {
    //id
    private Integer id;
    //角色id
    private Integer roleId;
    //权限id
    private Integer permissionId;
    //创建时间
    private String createTime;
    //修改时间
    private String updateTime;
    //创建人
    private User creator;
    //修改人
    private User modifier;

}
