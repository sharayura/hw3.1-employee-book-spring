package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    public List<Employee> departmentEmployees(int id) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
    }

    public int departmentSum(int id) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getId() == id)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getEmployeeSalaryMin(int id) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getId() == id)
                .mapToInt((Employee::getSalary))
                .min()
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Сотрудники не созданы"));
    }

    public int getEmployeeSalaryMax(int id) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getId() == id)
                .mapToInt((Employee::getSalary))
                .max()
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Сотрудники не созданы"));
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        Map<Integer, List<Employee>> groupedMap = new HashMap<>();
        for (Integer id : employeeService.getEmployees().keySet()) {
            if (!groupedMap.containsKey(id)) {
                groupedMap.put(id, departmentEmployees(id));
            }
        }
        return groupedMap;
    }

}


