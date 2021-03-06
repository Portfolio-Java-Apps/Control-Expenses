package dev.gclopes.ControlExpensesData.Services;

import dev.gclopes.ControlExpensesData.model.MovPersonalGoods;

import java.util.Set;


public interface MovPersonalGoodsService extends CrudService<MovPersonalGoods, Long> {
    Set<MovPersonalGoods> findAllByMovementId(Long id);
    Set<MovPersonalGoods> findAllByPersonalGoodsId(Long id);
    Double getTotalAmountByMovement(Long id);
    Double getTotalAmountByPersonalGoods(Long id);
}
