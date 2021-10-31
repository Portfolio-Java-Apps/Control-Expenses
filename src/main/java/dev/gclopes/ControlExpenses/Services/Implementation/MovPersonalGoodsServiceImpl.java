package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.Services.MovPersonalGoodsService;
import dev.gclopes.ControlExpenses.model.MovPersonalGoods;
import dev.gclopes.ControlExpenses.repositories.MovPersonalGoodsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
import java.util.HashSet;
import java.util.Set;

@Service("MovPersonalGoodsService")
public class MovPersonalGoodsServiceImpl implements MovPersonalGoodsService {
    private final MovPersonalGoodsRepository movPersonalGoodsRepository;

    public MovPersonalGoodsServiceImpl(MovPersonalGoodsRepository movPersonalGoodsRepository) {
        this.movPersonalGoodsRepository = movPersonalGoodsRepository;
    }
    @Override
    public Set<MovPersonalGoods> findAll() {
        Set<MovPersonalGoods> MovPersonalGoods = new HashSet<>();
        movPersonalGoodsRepository.findAll().forEach(MovPersonalGoods::add);
        return MovPersonalGoods;
    }

    @Override
    public MovPersonalGoods findById(Long aLong) throws NotSupportedException {
        throw new NotSupportedException("Cannot insert a personal good in a movement using only one id.");
    }

    @Override
    public MovPersonalGoods save(MovPersonalGoods object) {
        return movPersonalGoodsRepository.save(object);
    }

    @Override
    public void delete(MovPersonalGoods object) {
        movPersonalGoodsRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) throws NotSupportedException {
        throw new NotSupportedException("Cannot delete a personal good in a movement using only one id.");
    }

    public void deleteByMovementIdAndPersonalGoodsId(Long movementId, Long PersonalGoodsId) {
        movPersonalGoodsRepository.deleteByMovementIdAndPersonalGoodsId(movementId, PersonalGoodsId);
    }

    @Override
    public Set<MovPersonalGoods> findAllByMovementId(Long id) {
        return movPersonalGoodsRepository.findAllByMovementId(id);
    }

    @Override
    public Set<MovPersonalGoods> findAllByPersonalGoodsId(Long id) {
        return movPersonalGoodsRepository.findAllByPersonalGoodsId(id);
    }

    @Override
    public Double getTotalAmountByMovement(Long id) {
        return movPersonalGoodsRepository.getTotalAmountByMovement(id);
    }

    @Override
    public Double getTotalAmountByPersonalGoods(Long id) {
        return movPersonalGoodsRepository.getTotalAmountByPersonalGoods(id);
    }
}
