package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepartmentServiceTest {

    private final EmployeeService employeeServiceMock = mock(EmployeeService.class);

    @InjectMocks
    private DepartmentService departmentService;
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;
    private Employee employee5;
    private List<Employee> list;

    @BeforeAll
    public void setUp() {
        employee1 = new Employee("TestOne", "TestOne", 1, 20000);
        employee2 = new Employee("TestTwo", "TestTwo", 2, 40000);
        employee3 = new Employee("TestThree", "TestThree", 1, 30000);
        employee4 = new Employee("TestFour", "TestFour", 2, 45000);
        employee5 = new Employee("TestFive", "TestFive", 3, 35000);
        list = List.of(employee1, employee2, employee3, employee4, employee5);

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(list);
    }

    @Test
    public void departmentOneEmployees() {
        Assertions.assertEquals(List.of(employee1, employee3), departmentService.getDepartmentEmployees(1));
    }

    @Test
    public void departmentTwoEmployees() {
        Assertions.assertEquals(List.of(employee2, employee4), departmentService.getDepartmentEmployees(2));
    }

    @Test
    public void getDepartmentTwoSum() {
        Assertions.assertEquals(85000, departmentService.getDepartmentSum(2));
    }

    @Test
    public void getEmployeeSalaryOneMin() {
        Assertions.assertEquals(20000, departmentService.getEmployeeSalaryMin(1));
    }

    @Test
    public void getEmployeeSalaryTwoMax() {
        Assertions.assertEquals(45000, departmentService.getEmployeeSalaryMax(2));
    }

}
