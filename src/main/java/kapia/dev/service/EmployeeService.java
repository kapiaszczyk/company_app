package kapia.dev.service;

import jakarta.persistence.EntityNotFoundException;
import kapia.dev.dto.EmployeeWithLowestSalaryPerDepartment;
import kapia.dev.dto.MatchingSalaryAndCommission;
import kapia.dev.model.Employee;
import kapia.dev.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<EmployeeWithLowestSalaryPerDepartment> findAllWithLowestSalaryInDepartment() {
        return employeeRepository.findAllWithLowestSalaryInDepartment();
    }

    public List<MatchingSalaryAndCommission> findAllWithSameSalaryAndCommission(String lastName) {
        return employeeRepository.findAllWithSameSalaryAndCommission(lastName);
    }


    // This makes Spring create a proxy that wraps the method call in a transaction - that is why the constructor cannot be private
    @Transactional
    public List<Employee> setAsManager(int[] employeeIdList, Integer managerId) {
        // Fetch the manager entity
        Employee manager = employeeRepository.findById((long) managerId)
                .orElseThrow(() -> new EntityNotFoundException("Manager not found with ID: " + managerId));

        // Update the manager for each employee in the list
        for (int employeeId : employeeIdList) {
            Employee employee = employeeRepository.findById((long) employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

            // Set the new manager
            employee.setManagerId(manager.getEmployeeId());

            // Save the updated employee
            employeeRepository.save(employee);
        }

        return employeeRepository.findAllByManagerId(managerId);
    }

    public Employee updateSalary(Integer employeeId, Integer salary) {
        Employee employee = employeeRepository.findById((long) employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

        employee.setSalary(salary);

        return employeeRepository.save(employee);
    }

    public Void delete(Integer employeeId) {
        Employee employee = employeeRepository.findById((long) employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

        if (employeeRepository.existsByManagerId(employeeId)) {
            throw new IllegalArgumentException("Employee is a manager and cannot be deleted");
        }

        employeeRepository.delete(employee);

        return null;
    }

    public Iterable<Employee> findByDepartment(Integer departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
