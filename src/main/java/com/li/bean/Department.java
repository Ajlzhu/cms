package com.li.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

/**
 * @author licheng
 * @description 部门
 * @create 2018/11/22 21:21
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class Department implements Serializable {
    //部门id
    private Integer deptId;
    //部门名称
    private String deptName;
    //部门状态
    private Integer status;
    //上级部门id
    private Integer parentId;
    //创建人
    private String creator;
    private Integer createBy;
    //创建时间
    private String createTime;
    //修改人
    private String modifier;
    private Integer updateBy;
    //修改时间
    private String updateTime;

}
