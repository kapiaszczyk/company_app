package kapia.dev.controller;

import kapia.dev.dto.EmployeeLowestSalaryDTO;
import kapia.dev.model.Employee;
import kapia.dev.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/lowest-salary-in-department")
    public ResponseEntity<Iterable<EmployeeLowestSalaryDTO>> lowerSalary() {
        return new ResponseEntity<>(employeeService.findAllWithLowestSalaryInDepartment(), HttpStatus.OK);
    }

}
