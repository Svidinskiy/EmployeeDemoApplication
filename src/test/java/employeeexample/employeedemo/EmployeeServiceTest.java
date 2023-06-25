package employeeexample.employeedemo;

import employeeexample.employeedemo.exceptions.EmployeeAlreadyAddedException;
import employeeexample.employeedemo.exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    public void testAddEmployee() {
        Employee employee = employeeService.addEmployee("Дмитрий", "Ломов", 7000.0, "1");

        assertNotNull(employee);
        assertEquals("Дмитрий", employee.getFirstName());
        assertEquals("Ломов", employee.getLastName());
        assertEquals(7000.0, employee.getSalary(), 0.01);
        assertEquals("1", employee.getDepartment());

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee("Дмитрий", "Ломов", 7000.0, "1")
        );
    }

    @Test
    public void testRemoveEmployee() {
        employeeService.addEmployee("Максим", "Попов", 5000.0, "2");

        Employee removedEmployee = employeeService.removeEmployee("Максим", "Попов");
        Assertions.assertNotNull(removedEmployee);
        Assertions.assertEquals("Максим", removedEmployee.getFirstName());
        Assertions.assertEquals("Попов", removedEmployee.getLastName());
        Assertions.assertEquals(5000.0, removedEmployee.getSalary());
        Assertions.assertEquals("2", removedEmployee.getDepartment());

        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.removeEmployee("Михаил", "Дубов")
        );
    }

    @Test
    public void testFindEmployee() {
        employeeService.addEmployee("Лев", "Костин", 5000.0, "3");

        Employee foundEmployee = employeeService.findEmployee("Лев", "Костин");
        Assertions.assertNotNull(foundEmployee);
        Assertions.assertEquals("Лев", foundEmployee.getFirstName());
        Assertions.assertEquals("Костин", foundEmployee.getLastName());
        Assertions.assertEquals(5000.0, foundEmployee.getSalary());
        Assertions.assertEquals("3", foundEmployee.getDepartment());

        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.findEmployee("Анна", "Смит")
        );
    }

}

