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
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
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

    @PatchMapping(value = "/set-as-manager", consumes = "application/json")
    public ResponseEntity<Iterable<Employee>> setAsManager(@RequestBody int[] employeeIdList, @RequestParam(value = "manager_id") Integer managerId) {
        return new ResponseEntity<>(employeeService.setAsManager(employeeIdList, managerId), HttpStatus.CREATED);
    }

    @PatchMapping("/update-salary")
    public ResponseEntity<Employee> updateSalary(@RequestParam(value = "employee_id") Integer employeeId, @RequestParam(value = "salary") Integer salary) {
        return new ResponseEntity<>(employeeService.updateSalary(employeeId, salary), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam(value = "employee_id") Integer employeeId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-by-department")
    public ResponseEntity<Iterable<Employee>> findByDepartment(@RequestParam(value = "department_id") Integer departmentId) {
        return new ResponseEntity<>(employeeService.findByDepartment(departmentId), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }
}
