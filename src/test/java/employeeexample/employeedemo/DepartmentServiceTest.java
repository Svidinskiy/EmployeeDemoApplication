package employeeexample.employeedemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        departmentService = new DepartmentServiceImpl(employeeService);
    }

    @Test
    public void testGetEmployeesByDepartment() {
        String departmentId = "1";
        Employee employee1 = new Employee("Алексей", "Воробьев", 5000, departmentId);
        Employee employee2 = new Employee("Алена", "Орлова", 6000, departmentId);
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.getEmployeesByDepartment(departmentId)).thenReturn(employees);

        List<Employee> result = departmentService.getEmployeesByDepartment(departmentId);

        assertEquals(2, result.size());
        assertEquals("Алексей", result.get(0).getFirstName());
        assertEquals("Воробьев", result.get(0).getLastName());
        assertEquals(5000, result.get(0).getSalary());
        assertEquals("Алена", result.get(1).getFirstName());
        assertEquals("Орлова", result.get(1).getLastName());
        assertEquals(6000, result.get(1).getSalary());

        verify(employeeService, times(1)).getEmployeesByDepartment(departmentId);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testGetEmployeesByDepartmentNonExistentDepartment() {
        String departmentId = "10";

        when(employeeService.getEmployeesByDepartment(departmentId)).thenReturn(Collections.emptyList());

        List<Employee> result = departmentService.getEmployeesByDepartment(departmentId);

        Assertions.assertTrue(result.isEmpty());

        verify(employeeService, times(1)).getEmployeesByDepartment(departmentId);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void testGetSalarySumByDepartment() {
        String departmentId = "2";
        List<Employee> employees = Arrays.asList(
                new Employee("Андрей", "Зайцев", 5000, departmentId),
                new Employee("Антон", "Гусев", 6000, departmentId)
        );

        when(employeeService.getEmployeesByDepartment(departmentId)).thenReturn(employees);

        double expectedSalarySum = 11000;
        double actualSalarySum = departmentService.getSalarySumByDepartment(departmentId);

        assertEquals(expectedSalarySum, actualSalarySum);
        verify(employeeService).getEmployeesByDepartment(departmentId);
    }

    @Test
    void testGetMaxSalaryByDepartment() {
        String departmentId = "3";
        List<Employee> employees = Arrays.asList(
                new Employee("Михаил", "Кисилев", 5000, departmentId),
                new Employee("Мария", "Кузьмина", 6000, departmentId)
        );

        when(employeeService.getEmployeesByDepartment(departmentId)).thenReturn(employees);

        double expectedMaxSalary = 6000;
        double actualMaxSalary = departmentService.getMaxSalaryByDepartment(departmentId);

        assertEquals(expectedMaxSalary, actualMaxSalary);
        verify(employeeService).getEmployeesByDepartment(departmentId);
    }

    @Test
    void testGetMinSalaryByDepartment() {
        String departmentId = "4";
        List<Employee> employees = Arrays.asList(
                new Employee("Иван", "Иванов", 5000, departmentId),
                new Employee("Михаил", "Борисов", 6000, departmentId)
        );

        when(employeeService.getEmployeesByDepartment(departmentId)).thenReturn(employees);

        double expectedMinSalary = 5000;
        double actualMinSalary = departmentService.getMinSalaryByDepartment(departmentId);

        assertEquals(expectedMinSalary, actualMinSalary);
        verify(employeeService).getEmployeesByDepartment(departmentId);
    }

    @Test
    public void testGetAllEmployeesByDepartmentMap() {
        when(employeeService.getAllEmployeesByDepartment()).thenReturn(Map.of(
                "7", Arrays.asList(
                        new Employee("Марина", "Петрова", 5000.0, "7"),
                        new Employee("Артем", "Королев", 6000.0, "7")
                ),
                "8", Arrays.asList(
                        new Employee("Алиса", "Лазарева", 7000.0, "8"),
                        new Employee("Александр", "Яковлев", 8000.0, "8")
                )
        ));

        Map<String, List<Employee>> employeesByDepartment = departmentService.getEmployeesByDepartmentMap();
        Assertions.assertEquals(2, employeesByDepartment.size());
        Assertions.assertEquals(2, employeesByDepartment.get("7").size());
        Assertions.assertEquals(2, employeesByDepartment.get("8").size());

        verify(employeeService, times(1)).getAllEmployeesByDepartment();
    }

}


