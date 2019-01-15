package com.li.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author licheng
 * @description 用户
 * @create 2018/11/12 14:30
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {
    //用户id
    private Integer userId;
    //用户名
    private String username;
    //昵称
    private String nickname;
    //手机号码
    private String phone;
    //邮箱
    private String email;
    //密码
    @JsonIgnore
    private String password;
    //用户状态 0：禁用 1：启用
    private Integer status;
    //创建时间
    private String createTime;
    //修改时间
    private String updateTime;
    //创建人
    private User creator;
    //修改人
    private User modifier;
    //用户部门
    private Department dept;
    //用户角色信息
    private List<Role> roleList;

}
