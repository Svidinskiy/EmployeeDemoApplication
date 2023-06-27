package employeeexample.employeedemo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, double salary, String department);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    Employee getEmployeeWithMaxSalaryByDepartment(String departmentId);
    Employee getEmployeeWithMinSalaryByDepartment(String departmentId);
    List<Employee> getEmployeesByDepartment(String departmentId);
    Map<String, List<Employee>> getAllEmployeesByDepartment();
    Collection<Employee> getAllEmployees();

}
