package employeeexample.employeedemo;

import employeeexample.employeedemo.exceptions.EmployeeAlreadyAddedException;
import employeeexample.employeedemo.exceptions.EmployeeNotFoundException;
import employeeexample.employeedemo.exceptions.EmployeeStorageIsFullException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam String department) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) throws EmployeeNotFoundException {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) throws EmployeeNotFoundException {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/list")
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/departments/max-salary")
    public Employee getEmployeeWithMaxSalaryByDepartment(@RequestParam String departmentId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeWithMaxSalaryByDepartment(departmentId);
    }

    @GetMapping("/departments/min-salary")
    public Employee getEmployeeWithMinSalaryByDepartment(@RequestParam String departmentId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeWithMinSalaryByDepartment(departmentId);
    }

    @GetMapping("/departments")
    public List<Employee> getEmployeesByDepartment(@RequestParam String departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }

    @GetMapping("/departments/all")
    public Map<String, List<Employee>> getAllEmployeesByDepartmentMap() {
        return employeeService.getAllEmployeesByDepartment();
    }

}

