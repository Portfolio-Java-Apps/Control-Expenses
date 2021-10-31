package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.model.MovPersonalGoods;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovPersonalGoodsRepository extends CrudRepository<MovPersonalGoods, Long> {
    Set<MovPersonalGoods> findAllByMovementId(Long id);
    Set<MovPersonalGoods> findAllByPersonalGoodsId(Long id);
    @Query(value="SELECT SUM(amount) FROM mov_personal_goods  WHERE movement_id = :id", nativeQuery = true)
    Double getTotalAmountByMovement(@Param("id") Long id);
    @Query(value="SELECT SUM(amount) FROM mov_personal_goods  WHERE m.personal_goods_id = :id", nativeQuery = true)
    Double getTotalAmountByPersonalGoods(@Param("id") Long id);

    void deleteByMovementIdAndPersonalGoodsId(Long movementId, Long personalGoodsId);
}
