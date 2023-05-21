package employeeexample.employeedemo;

import employeeexample.employeedemo.exceptions.EmployeeAlreadyAddedException;
import employeeexample.employeedemo.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();


    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(newEmployee.getKey())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(newEmployee.getKey(), newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (employees.containsKey(employeeToRemove.getKey())) {
            return employees.remove(employeeToRemove.getKey());
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employeeFind = new Employee(firstName, lastName);
        if (employees.containsKey(employeeFind.getKey())) {
            return employees.get(employeeFind.getKey());
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }



}

