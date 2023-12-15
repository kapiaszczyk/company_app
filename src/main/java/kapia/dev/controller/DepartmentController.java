package kapia.dev.controller;

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

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/with-id-name-manager-id")
    public ResponseEntity<List<DepartmentIdNameManagerId>> findAllWithIdNameManagerId() {
        return new ResponseEntity<>(departmentService.findAllWithIdNameManagerId(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/id/{department_id}/location-id/{location_id}")
    public ResponseEntity<Department> updateDepartmentLocation(@PathVariable(value = "department_id") Long departmentId, @PathVariable(value = "location_id") Long locationId) {
        return new ResponseEntity<>(departmentService.updateLocation(departmentId, locationId), HttpStatus.OK);
    }

}
