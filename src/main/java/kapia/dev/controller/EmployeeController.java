package kapia.dev.controller;

import kapia.dev.dto.EmployeeWithLowestSalaryPerDepartment;
import kapia.dev.dto.MatchingSalaryAndCommission;
import kapia.dev.model.Employee;
import kapia.dev.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/lowest-salary-in-department")
    public ResponseEntity<Iterable<EmployeeWithLowestSalaryPerDepartment>> lowerSalary() {
        return new ResponseEntity<>(employeeService.findAllWithLowestSalaryInDepartment(), HttpStatus.OK);
    }

    @GetMapping("/same-salary-and-commission-as")
    public ResponseEntity<Iterable<MatchingSalaryAndCommission>> sameSalaryAndCommission(@RequestParam("last_name") String lastName) {
        return new ResponseEntity<>(employeeService.findAllWithSameSalaryAndCommission(lastName), HttpStatus.OK);
    }

    @PatchMapping(value = "/manager-id/{manager_id}", consumes = "application/json")
    public ResponseEntity<Iterable<Employee>> setAsManager(@RequestBody int[] employeeIdList, @PathVariable(value = "manager_id") Integer managerId) {
        return new ResponseEntity<>(employeeService.setAsManager(employeeIdList, managerId), HttpStatus.CREATED);
    }

    @PatchMapping("/id/{employee_id}/salary/{salary}")
    public ResponseEntity<Employee> updateSalary(@PathVariable(value = "employee_id") Integer employeeId, @PathVariable(value = "salary") Integer salary) {
        return new ResponseEntity<>(employeeService.updateSalary(employeeId, salary), HttpStatus.OK);
    }

    @DeleteMapping("/id/{employee_id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "employee_id") Integer employeeId) {
        try {
            return new ResponseEntity<>(employeeService.delete(employeeId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/id/{department_id}")
    public ResponseEntity<Iterable<Employee>> findByDepartment(@PathVariable(value = "department_id") Integer departmentId) {
        return new ResponseEntity<>(employeeService.findByDepartment(departmentId), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }
}
