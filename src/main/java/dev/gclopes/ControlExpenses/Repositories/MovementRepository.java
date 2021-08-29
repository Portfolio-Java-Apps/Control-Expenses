package dev.gclopes.ControlExpenses.Repositories;

import dev.gclopes.ControlExpenses.Model.Movement;
import org.springframework.data.repository.CrudRepository;

public interface MovementRepository extends CrudRepository<Movement, Long> {
}
