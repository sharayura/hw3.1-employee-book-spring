package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();

    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
            return this.employeeService.addEmployee(employeeRequest);
    }

    @RequestMapping("/add1")
    public Employee add1(String first, String last) {
        return this.employeeService.add1(first, last);
    }

    @GetMapping("employees/salary/sum")
    public int getSalarySum() {
        return employeeService.getSalarySum();
    }

    @GetMapping("employees/salary/min")
    public Employee getSalaryMin() {
        return employeeService.getEmployeeSalaryMin();
    }

    @GetMapping("employees/salary/max")
    public Employee getSalaryMax() {
        return employeeService.getEmployeeSalaryMax();
    }

    @GetMapping("employees/high-salary")
    public List<Employee> getSalaryAverage() {
        return employeeService.getEmployeeSalaryAverage();
    }


}
