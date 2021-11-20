package dev.gclopes.ControlExpensesData.Services;

import dev.gclopes.ControlExpensesData.model.PersonalGoods;

public interface PersonalGoodsService extends CrudService<PersonalGoods, Long> {
    PersonalGoods findByName(String name);
}
