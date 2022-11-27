package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/{id}/employees")
    public List<Employee> departmentEmployees(@PathVariable("id") int departmentId) {
        return departmentService.departmentEmployees(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public int departmentSum(@PathVariable("id") int departmentId) {
        return departmentService.getDepartmentSum(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public int getEmployeeSalaryMin(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeeSalaryMin(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public int getEmployeeSalaryMax(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeeSalaryMax(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return departmentService.getEmployeesGroupedByDepartment();
    }

}
