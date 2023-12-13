package kapia.dev.service;

import jakarta.persistence.EntityNotFoundException;
import kapia.dev.model.Department;
import kapia.dev.model.Location;
import kapia.dev.repository.DepartmentRepository;
import kapia.dev.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long departmentId, Long locationId) {
        // find department by id
        Department departmentToUpdate = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + departmentId));

        // update department
        Location newLocation = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + locationId));

        departmentToUpdate.setLocation(newLocation);

        return departmentRepository.save(departmentToUpdate);

    }
}
