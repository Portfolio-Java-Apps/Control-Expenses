package dev.gclopes.ControlExpensesData.repositories;

import dev.gclopes.ControlExpensesData.model.PersonalGoods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalGoodsRepository extends CrudRepository<PersonalGoods, Long> {
    Optional<PersonalGoods> findByName(String name);
}
