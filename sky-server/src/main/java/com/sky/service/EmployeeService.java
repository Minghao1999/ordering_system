package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * ADD NEW EMPLOYEE
     * @param employeeDTO
     * @return
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * Pagination Query
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * START OR STOP EMPLOYEE ACCOUNT
     *
     * @param status
     * @param id
     * @return
     */
    void startOrStop(Integer status, Long id);

    /**
     *QUERY EMPLOYEE INFO BY ID
     *
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     *UPDATE EMPLOYEE INFO
     *
     * @param employeeDTO
     * @return
     */
    void update(EmployeeDTO employeeDTO);
}
