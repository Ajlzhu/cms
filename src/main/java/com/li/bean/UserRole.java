package com.li.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @author licheng
 * @description 用户角色
 * @create 2018/11/12 14:57
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRole {
    //id
    private Integer id;
    //用户id
    private Integer userId;
    //角色id
    private Integer roleId;
    //创建时间
    private String createTime;
    //修改时间
    private String updateTime;
    //创建人
    private User creator;
    //修改人
    private User modifier;

}
