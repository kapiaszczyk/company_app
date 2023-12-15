package kapia.dev.repository;

import kapia.dev.dto.EmployeeWithLowestSalaryPerDepartment;
import kapia.dev.dto.MatchingSalaryAndCommission;
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
    List<EmployeeWithLowestSalaryPerDepartment> findAllWithLowestSalaryInDepartment();

    // Get first name, last name, hire date, salary for all employees who have the same salary and commission as a given employee
    // TODO: Validate if the query gives the correct result
    @Query(
            value = "SELECT" +
                    "    emp.first_name AS firstName, " +
                    "    emp.last_name AS lastName, " +
                    "    emp.hire_date AS hireDate, " +
                    "    emp.salary AS salary " +
                    "FROM " +
                    "    employees emp " +
                    "WHERE " +
                    "    (emp.salary, NVL(emp.commission_pct, 0)) " +
                    "IN (" +
                    "    SELECT salary, NVL(commission_pct, 0) " +
                    "    FROM employees " +
                    "    WHERE last_name = ?1 " +
                    ") " +
                    "AND emp.last_name != ?1",
            nativeQuery = true
    )
    List<MatchingSalaryAndCommission> findAllWithSameSalaryAndCommission(String lastName);

    @Query(
            value = "UPDATE employees " +
                    "SET manager_id = ?2 " +
                    "WHERE employee_id IN ?1",
            nativeQuery = true
    )
    List<Employee> setAsManager(int[] employeeIdList, Integer managerId);

    List<Employee> findAllByManagerId(Integer managerId);

}
