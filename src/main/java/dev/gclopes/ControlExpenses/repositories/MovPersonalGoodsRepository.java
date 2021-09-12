package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.model.MovPersonalGoods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovPersonalGoodsRepository extends CrudRepository<MovPersonalGoods, Long> {
}
