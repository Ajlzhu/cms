package com.li.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/12 14:40
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class Role implements Serializable {
    //角色id
    private Integer roleId;
    //角色名称
    private String roleName;
    //创建人
    //创建时间
    private String createTime;
    //修改时间
    private String updateTime;
    //创建人
    private User creator;
    //修改人
    private User modifier;
    //权限集合
    private List<Permission> permissionList;
    //用户集合
    private List<User> userList;

}
