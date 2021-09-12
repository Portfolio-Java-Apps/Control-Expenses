package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.model.PersonalGoods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalGoodsRepository extends CrudRepository<PersonalGoods, Long> {
}
