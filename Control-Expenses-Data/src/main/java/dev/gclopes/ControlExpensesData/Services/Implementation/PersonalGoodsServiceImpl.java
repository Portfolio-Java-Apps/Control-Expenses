package dev.gclopes.ControlExpensesData.Services.Implementation;

import dev.gclopes.ControlExpensesData.Services.PersonalGoodsService;
import dev.gclopes.ControlExpensesData.model.PersonalGoods;
import dev.gclopes.ControlExpensesData.repositories.PersonalGoodsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("PersonalGoodsService")
public class PersonalGoodsServiceImpl implements PersonalGoodsService {
    private final PersonalGoodsRepository personalGoodsRepository;

    public PersonalGoodsServiceImpl(PersonalGoodsRepository personalGoodsRepository) {
        this.personalGoodsRepository = personalGoodsRepository;
    }

    @Override
    public Set<PersonalGoods> findAll() {
        Set<PersonalGoods> personalGoods = new HashSet<>();
        personalGoodsRepository.findAll().forEach(personalGoods::add);
        return personalGoods;
    }

    @Override
    public PersonalGoods findById(Long aLong) {
        return personalGoodsRepository.findById(aLong).orElse(null);
    }

    @Override
    public PersonalGoods save(PersonalGoods object) {
        return personalGoodsRepository.save(object);
    }

    @Override
    public void delete(PersonalGoods object) {
        personalGoodsRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        personalGoodsRepository.deleteById(aLong);
    }

    @Override
    public PersonalGoods findByName(String name) {
        return personalGoodsRepository.findByName(name).orElse(null);
    }
}
