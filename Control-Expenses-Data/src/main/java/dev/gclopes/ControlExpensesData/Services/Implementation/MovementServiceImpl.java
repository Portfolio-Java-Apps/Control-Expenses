package dev.gclopes.ControlExpensesData.Services.Implementation;

import dev.gclopes.ControlExpensesData.Services.MovementService;
import dev.gclopes.ControlExpensesData.enumType.Type;
import dev.gclopes.ControlExpensesData.model.Movement;
import dev.gclopes.ControlExpensesData.repositories.MovementRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service("MovementService")
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;

    public MovementServiceImpl(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public Set<Movement> findAll() {
        Set<Movement> movements = new HashSet<>();
        movementRepository.findAll().forEach(movements::add);
        return movements;
    }

    @Override
    public Movement findById(Long aLong) {
        return movementRepository.findById(aLong).orElse(null);
    }

    @Override
    public Movement save(Movement object) {
        return movementRepository.save(object);
    }

    @Override
    public void delete(Movement object) {
        movementRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        movementRepository.deleteById(aLong);
    }

    @Override
    public Set<Movement> findByType(Type type) {
        return new HashSet<>(movementRepository.findByType(type));
    }

    @Override
    public Set<Movement> findByDate(Date date) {
        return new HashSet<>(movementRepository.findByDate(date));
    }

    @Override
    public Double getTotalAmountByDate(Date date) {
        return movementRepository.getTotalAmountByDate(date);
    }

    @Override
    public Double getTotalAmountByMonth(Integer month) {
        return movementRepository.getTotalAmountByMonth(month);
    }

    @Override
    public Double getTotalAmountByYear(Integer year) {
        return movementRepository.getTotalAmountByYear(year);
    }

    @Override
    public Double getTotalDiscountByDate(Date date) {
        return movementRepository.getTotalDiscountByDate(date);
    }

    @Override
    public Double getTotalDiscountByMonth(Integer month) {
        return movementRepository.getTotalDiscountByMonth(month);
    }

    @Override
    public Double getTotalDiscountByYear(Integer year) {
        return movementRepository.getTotalDiscountByYear(year);
    }
}
