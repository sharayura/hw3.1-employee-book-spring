package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (StringUtils.isBlank(employeeRequest.getFirstName()) || StringUtils.isBlank(employeeRequest.getLastName())) {
            throw new IllegalArgumentException("Заполните имя");
        }
        if (!StringUtils.isAlpha(employeeRequest.getFirstName()) || !StringUtils.isAlpha(employeeRequest.getLastName())) {
            throw new IllegalArgumentException("Неверный формат имени");
        }
        employeeRequest.setFirstName(StringUtils.capitalize(employeeRequest.getFirstName()));
        employeeRequest.setLastName(StringUtils.capitalize(employeeRequest.getLastName()));

        Employee employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName(),
                employeeRequest.getDepartment(), employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .sum();

    }

    public Employee getEmployeeSalaryMin() {
        return employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseGet(() -> null);
    }

    public Employee getEmployeeSalaryMax() {
        return employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseGet(() -> null);
    }

    public List<Employee> getEmployeeSalaryAverage() {
        double averageSalary = employees.values().stream()
                .mapToInt(e -> e.getSalary())
                .average().orElse(0);
        return employees.values().stream()
                .filter(e -> e.getSalary() > averageSalary)
                .collect(Collectors.toList());
    }

}
