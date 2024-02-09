package kapia.dev.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Hidden
    public ResponseEntity<Iterable<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get employees by lowest salary in department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employees",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeWithLowestSalaryPerDepartment.class)) }),
            @ApiResponse(responseCode = "404", description = "Employees not found",
                    content = @Content) })
    @GetMapping("/lowest-salary-in-department")
    public ResponseEntity<Iterable<EmployeeWithLowestSalaryPerDepartment>> lowerSalary() {
        return new ResponseEntity<>(employeeService.findAllWithLowestSalaryInDepartment(), HttpStatus.OK);
    }

    @Operation(summary = "Get employees with same salary and commission as other employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employees",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MatchingSalaryAndCommission.class)) }),
            @ApiResponse(responseCode = "404", description = "Employees not found",
                    content = @Content) })
    @GetMapping("/same-salary-and-commission-as")
    public ResponseEntity<Iterable<MatchingSalaryAndCommission>> sameSalaryAndCommission(@RequestParam("last_name") String lastName) {
        return new ResponseEntity<>(employeeService.findAllWithSameSalaryAndCommission(lastName), HttpStatus.OK);
    }

    @Operation(summary = "Set an employee as manager for multiple employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the employees",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) }),
            @ApiResponse(responseCode = "404", description = "Employees not found",
                    content = @Content) })
    @PatchMapping(value = "/manager-id/{manager_id}", consumes = "application/json")
    public ResponseEntity<Iterable<Employee>> setAsManager(@RequestBody int[] employeeIdList, @PathVariable(value = "manager_id") Integer managerId) {
        return new ResponseEntity<>(employeeService.setAsManager(employeeIdList, managerId), HttpStatus.CREATED);
    }

    @Hidden
    @PatchMapping("/id/{employee_id}/salary/{salary}")
    public ResponseEntity<Employee> updateSalary(@PathVariable(value = "employee_id") Integer employeeId, @PathVariable(value = "salary") Integer salary) {
        return new ResponseEntity<>(employeeService.updateSalary(employeeId, salary), HttpStatus.OK);
    }

    @Operation(summary = "Delete an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the employee",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) }),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content) })
    @DeleteMapping("/id/{employee_id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "employee_id") Integer employeeId) {
        try {
            return new ResponseEntity<>(employeeService.delete(employeeId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Hidden
    @GetMapping("/id/{department_id}")
    public ResponseEntity<Iterable<Employee>> findByDepartment(@PathVariable(value = "department_id") Integer departmentId) {
        return new ResponseEntity<>(employeeService.findByDepartment(departmentId), HttpStatus.OK);
    }

    @Hidden
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }
}
