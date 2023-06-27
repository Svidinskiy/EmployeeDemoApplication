package employeeexample.employeedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByDepartment(String departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }

    public double getSalarySumByDepartment(String departmentId) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(departmentId);
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public double getMaxSalaryByDepartment(String departmentId) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(departmentId);
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0);
    }

    public double getMinSalaryByDepartment(String departmentId) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(departmentId);
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);
    }

    public Map<String, List<Employee>> getEmployeesByDepartmentMap() {
        return employeeService.getAllEmployeesByDepartment();
    }

}
