package kapia.dev.repository;

import kapia.dev.dto.EmployeeLowestSalaryDTO;
import kapia.dev.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Get all employees whose salary is equal to minimal salary for a given department
    @Query(
            value = "WITH lowest_salary AS ( " +
                    "    SELECT department_id, MIN(salary) AS lowest_salary " +
                    "    FROM employees " +
                    "    GROUP BY department_id " +
                    ") " +
                    " " +
                    "SELECT emp.first_name AS firstName, " +
                    "emp.last_name AS lastName, " +
                    "emp.department_id AS departmentId, " +
                    "emp.salary AS salary " +
                    "FROM employees emp " +
                    "JOIN lowest_salary ls ON emp.department_id = ls.department_id " +
                    "WHERE emp.salary = ls.lowest_salary " +
                    "ORDER BY emp.department_id",
            nativeQuery = true
    )
    List<EmployeeLowestSalaryDTO> findAllWithLowestSalaryInDepartment();

}
