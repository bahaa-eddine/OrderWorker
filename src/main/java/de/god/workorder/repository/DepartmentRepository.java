package de.god.workorder.repository;

import de.god.workorder.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author belbaghazaoui
 *
 */
public interface DepartmentRepository extends JpaRepository<Department, String> {
}
