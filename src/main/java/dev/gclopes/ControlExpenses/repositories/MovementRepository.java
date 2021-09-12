package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.model.Movement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovementRepository extends CrudRepository<Movement, Long> {
    List<Movement> findByType(String Type);
    List<Movement> findByDate(Date date);

}
