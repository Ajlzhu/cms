package com.li.controller;

import com.li.bean.Department;
import com.li.bean.ResponseResult;
import com.li.service.DepartmentService;
import com.li.utils.ResponseResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author licheng
 * @description
 * @create 2018/11/22 22:02
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/list")
    public ResponseResult getDeptListByCondition(Department department){
        List<Department> deptList = departmentService.getDeptListByCondition(department);
        return ResponseResultUtil.genResult("获取部门信息成功", deptList);
    }



}
