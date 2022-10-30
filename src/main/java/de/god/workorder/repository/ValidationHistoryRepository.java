package de.god.workorder.repository;

import de.god.workorder.entity.ValidationHistory;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author belbaghazaoui
 *
 */
public interface ValidationHistoryRepository extends JpaRepository<ValidationHistory, Long> {
}
