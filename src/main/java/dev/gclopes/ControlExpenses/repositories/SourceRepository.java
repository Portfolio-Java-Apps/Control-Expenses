package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.model.Source;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRepository extends CrudRepository<Source, Long> {
    Optional<Source> findByDesc(String desc);
}
