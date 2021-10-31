package dev.gclopes.ControlExpenses.Services;

import dev.gclopes.ControlExpenses.model.PersonalGoods;

public interface PersonalGoodsService extends CrudService<PersonalGoods, Long> {
    PersonalGoods findByName(String name);
}
