package com.li.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author licheng
 * @description
 * @create 2018/11/12 14:51
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"handler"})
public class Permission implements Serializable {
    //权限id
    private Integer pId;
    //权限名称
    private String pName;
    //权限url
    private String pUrl;
    //权限码
    private String pCode;
    //权限类型
    private Integer type;
    //上级权限id
    private Integer parentId;
    //是否选中
    private boolean checked;
    //排序
    private Integer sort;
    //子权限
    private List<Permission> childPermission;
    //创建时间
    private String createTime;
    //修改时间
    private String updateTime;
    //角色集合
    private List<Role> roleList;
    //创建人
    private User creator;
    //修改人
    private User modifier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission permission = (Permission) o;
        return Objects.equals(pName, permission.pName) &&
                Objects.equals(pId, permission.pId);
    }
}
