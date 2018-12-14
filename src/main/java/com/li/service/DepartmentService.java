package com.li.service;

import com.li.bean.Department;

import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/22 21:59
 */
public interface DepartmentService {

    List<Department> getDeptListByCondition(Department department);
}
