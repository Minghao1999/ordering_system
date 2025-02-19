package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("员工退出")
    public Result<String> logout() {
        return Result.success();
    }
    /**
     * ADD NEW EMPLOYEE
     *
     * @param employeeDTO
     * @return
     */

    @PostMapping
    @ApiOperation("Add new employee")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("add new employee: {}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * PAGE QUERY
     *
     * @param employeePageQueryDTO
     * @return
     */

    @GetMapping("/page")
    @ApiOperation("Pagination Query")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("Pagination Query, param: {}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * START OR STOP EMPLOYEE ACCOUNT
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Start and Stop Employee Account")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("Start or Stop Employee Account, id: {}, status: {}", id, status);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     *QUERY EMPLOYEE INFO BY ID
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Query Employee Info By Id")
    public Result<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }
    /**
     *UPDATE EMPLOYEE INFO
     *
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Update Employee Info")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.update(employeeDTO);
        return Result.success();
    }
}
