package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getDepartmentEmployees(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public int getDepartmentSum(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getEmployeeSalaryMin(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToInt((Employee::getSalary))
                .min()
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Сотрудники не созданы"));
    }

    public int getEmployeeSalaryMax(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToInt((Employee::getSalary))
                .max()
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Сотрудники не созданы"));
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        Map<Integer, List<Employee>> groupedMap = new HashMap<>();
        for (Employee employee : employeeService.getEmployees().values()) {
            if (!groupedMap.containsKey(employee.getDepartment())) {
                groupedMap.put(employee.getDepartment(), getDepartmentEmployees(employee.getDepartment()));
            }
        }
        return groupedMap;
    }

}


