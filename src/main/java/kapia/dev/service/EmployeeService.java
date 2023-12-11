package kapia.dev.service;

import kapia.dev.dto.EmployeeWithLowestSalaryPerDepartment;
import kapia.dev.dto.MatchingSalaryAndCommission;
import kapia.dev.model.Employee;
import kapia.dev.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<EmployeeWithLowestSalaryPerDepartment> findAllWithLowestSalaryInDepartment() {
        return employeeRepository.findAllWithLowestSalaryInDepartment();
    }

    public List<MatchingSalaryAndCommission> findAllWithSameSalaryAndCommission(String lastName) {
        return employeeRepository.findAllWithSameSalaryAndCommission(lastName);
    }

}
