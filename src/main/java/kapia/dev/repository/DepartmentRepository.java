package kapia.dev.repository;

import kapia.dev.dto.DepartmentIdNameManagerId;
import kapia.dev.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(
            value = "SELECT " +
                    "    d.department_id AS departmentId, " +
                    "    d.department_name AS departmentName, " +
                    "    d.manager_id AS managerId " +
                    "FROM " +
                    "    departments d " +
                    "ORDER BY d.department_id",
            nativeQuery = true
    )
    List<DepartmentIdNameManagerId> findAllWithIdNameManagerId();
}
