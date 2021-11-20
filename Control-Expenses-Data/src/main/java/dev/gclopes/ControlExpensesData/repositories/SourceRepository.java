package dev.gclopes.ControlExpensesData.repositories;

import dev.gclopes.ControlExpensesData.model.Source;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRepository extends CrudRepository<Source, Long> {
    Optional<Source> findByName(String name);
}
