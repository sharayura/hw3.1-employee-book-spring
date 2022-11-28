package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeServiceTest {

    private EmployeeRequest employee1;
    private EmployeeRequest employee2;
    private EmployeeRequest employee3;
    private EmployeeRequest employee4;
    private EmployeeRequest employee5;
    private EmployeeRequest employeeFail;
    private EmployeeService employeeService;
    private List<Employee> averageExpected;


    @BeforeAll
    public void setup() {
        employee1 = new EmployeeRequest("TestOne", "TestOne", 1, 20000);
        employee2 = new EmployeeRequest("TestTwo", "TestTwo", 2, 40000);
        employee3 = new EmployeeRequest("TestThree", "TestThree", 1, 30000);
        employee4 = new EmployeeRequest("TestFour", "TestFour", 2, 45000);
        employee5 = new EmployeeRequest("TestFive", "TestFive", 3, 35000);

        employeeFail = new EmployeeRequest(null, "Test", 0, 0);

        employeeService = new EmployeeService();
        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);
        employeeService.addEmployee(employee4);
        employeeService.addEmployee(employee5);

    }

    @Test
    public void addEmployeeTest() {
        Assertions.assertThrows(BadRequestException.class, () -> employeeService.addEmployee(employeeFail));
    }

    @Test
    public void getSalarySumTest() {
        Assertions.assertEquals(170000, employeeService.getSalarySum());
    }

    @Test
    public void getEmployeeSalaryMinTest() {
        Assertions.assertEquals(employeeService.getEmployees().get(0), employeeService.getEmployeeSalaryMin());
    }
    @Test
    public void getEmployeeSalaryMaxTest() {
        Assertions.assertEquals(employeeService.getEmployees().get(3), employeeService.getEmployeeSalaryMax());
    }

    @Test
    public void getEmployeeSalaryAverageTest() {
        averageExpected = new ArrayList<>();
        averageExpected.add(employeeService.getEmployees().get(1));
        averageExpected.add(employeeService.getEmployees().get(3));
        averageExpected.add(employeeService.getEmployees().get(4));

        Assertions.assertEquals(averageExpected, employeeService.getEmployeeSalaryAverage());
    }
}
