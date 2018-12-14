package com.li.mapper;

import com.li.bean.Department;

import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/22 21:57
 */
public interface DepartmentMapper {

    List<Department> getDeptListByCondition(Department department);
}
