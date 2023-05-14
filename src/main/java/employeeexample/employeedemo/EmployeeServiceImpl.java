package employeeexample.employeedemo;

import employeeexample.employeedemo.exceptions.EmployeeAlreadyAddedException;
import employeeexample.employeedemo.exceptions.EmployeeNotFoundException;
import employeeexample.employeedemo.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxEmployee = 10;
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException();
        }
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(newEmployee);
        return newEmployee;
    }


    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = findEmployee(firstName, lastName);
        if (employeeToRemove != null) {
            employees.remove(employeeToRemove);
        } else {
            throw new EmployeeNotFoundException();
        }
        return employeeToRemove;
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getAllEmployees() {
        return Collections.unmodifiableList(employees);
    }



}

