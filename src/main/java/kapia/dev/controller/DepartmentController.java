package kapia.dev.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kapia.dev.dto.DepartmentIdNameManagerId;
import kapia.dev.model.Department;
import kapia.dev.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    private DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @Hidden
    public ResponseEntity<List<Department>> findAll() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Find all departments with id, name and manager id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employees",
            content = { @Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = DepartmentIdNameManagerId.class)) }),
            @ApiResponse(responseCode = "404", description = "Employees not found",
                    content = @Content)
    })
    @GetMapping("/with-id-name-manager-id")
    public ResponseEntity<List<DepartmentIdNameManagerId>> findAllWithIdNameManagerId() {
        return new ResponseEntity<>(departmentService.findAllWithIdNameManagerId(), HttpStatus.OK);
    }

    @Operation(summary = "Find department by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Department added",
                    content = { @Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Department.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)
    })
    @PostMapping(consumes = "application/json")
    @Hidden
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @Operation(summary = "Update department location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department location updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Department.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)
    })
    @PatchMapping(value = "/id/{department_id}/location-id/{location_id}")
    public ResponseEntity<Department> updateDepartmentLocation(@PathVariable(value = "department_id") Long departmentId, @PathVariable(value = "location_id") Long locationId) {
        return new ResponseEntity<>(departmentService.updateLocation(departmentId, locationId), HttpStatus.OK);
    }

}
