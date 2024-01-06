package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.Employee;
import source.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getEmployeeId() != null) {
            return employeeRepository.save(employee);
        }
        throw new IllegalArgumentException("Employee ID must be null for creation.");
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        if (employeeRepository.existsById(id)) {
            updatedEmployee.setEmployeeId(id);
            return employeeRepository.save(updatedEmployee);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.deleteById(id);
    }
}