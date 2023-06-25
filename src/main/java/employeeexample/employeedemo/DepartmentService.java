package employeeexample.employeedemo;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employee> getEmployeesByDepartment(String departmentId);
    double getSalarySumByDepartment(String departmentId);
    double getMaxSalaryByDepartment(String departmentId);
    double getMinSalaryByDepartment(String departmentId);
    Map<String, List<Employee>> getEmployeesByDepartmentMap();
}
