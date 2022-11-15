package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
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
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Заполните имя");
        }
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
                .get();
    }

    public Employee getEmployeeSalaryMax() {
        return employees.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
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
