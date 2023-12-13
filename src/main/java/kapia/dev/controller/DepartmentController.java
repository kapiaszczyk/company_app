package kapia.dev.controller;

import kapia.dev.model.Department;
import kapia.dev.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Department>> findAll() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(Department department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Department> updateDepartment(@RequestParam(value = "department_id") Long departmentId, @RequestParam(value = "location_id") Long locationId) {
        return new ResponseEntity<>(departmentService.updateDepartment(departmentId, locationId), HttpStatus.OK);
    }

}
