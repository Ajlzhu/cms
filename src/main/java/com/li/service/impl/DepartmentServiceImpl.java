package com.li.service.impl;

import com.li.bean.Department;
import com.li.mapper.DepartmentMapper;
import com.li.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/22 22:00
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 根据条件获取部门
     * @param department
     * @return java.util.List<com.li.bean.Department>
     */
    @Override
    public List<Department> getDeptListByCondition(Department department) {
        logger.info("查询部门信息");
        return departmentMapper.getDeptListByCondition(department);
    }
}
