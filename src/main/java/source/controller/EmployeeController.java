package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source.dto.Employee;
import source.service.EmployeeService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Employee employeeResult = employeeService.getEmployeeById(id).orElse(null);
        if (employeeResult == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with id: " + id);
        }
        return ResponseEntity.ok(employeeResult);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            if (employee == null) {
                return ResponseEntity.badRequest().body("Employee data is missing");
            }
            Employee createdEmployee = employeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        try {
            Employee employeeResult = employeeService.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(employeeResult);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
