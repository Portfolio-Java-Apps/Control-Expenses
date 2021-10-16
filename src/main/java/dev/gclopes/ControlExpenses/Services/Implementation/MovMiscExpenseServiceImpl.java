package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.Services.MovMiscExpenseService;
import dev.gclopes.ControlExpenses.model.MovMiscExpense;
import dev.gclopes.ControlExpenses.repositories.MovMiscExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("MovMiscExpenseService")
public class MovMiscExpenseServiceImpl implements MovMiscExpenseService {
    private final MovMiscExpenseRepository movMiscExpenseRepository;

    public MovMiscExpenseServiceImpl(MovMiscExpenseRepository movMiscExpenseRepository) {
        this.movMiscExpenseRepository = movMiscExpenseRepository;
    }
    @Override
    public Set<MovMiscExpense> findAll() {
        Set<MovMiscExpense> movMiscExpenses = new HashSet<>();
        movMiscExpenseRepository.findAll().forEach(movMiscExpenses::add);
        return movMiscExpenses;
    }

    @Override
    public MovMiscExpense findById(Long aLong) {
        return null;
    }

    @Override
    public MovMiscExpense save(MovMiscExpense object) {
        return movMiscExpenseRepository.save(object);
    }

    @Override
    public void delete(MovMiscExpense object) {
        movMiscExpenseRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        movMiscExpenseRepository.deleteById(aLong);
    }

    @Override
    public Set<MovMiscExpense> findAllByIdmovementId(Long id) {
        return movMiscExpenseRepository.findAllByIdmovementId(id);
    }

    @Override
    public Set<MovMiscExpense> findAllByIdmiscExpenseId(Long id) {
        return movMiscExpenseRepository.findAllByIdmiscExpenseId(id);
    }

    @Override
    public Double getTotalAmountByMovement(Long id) {
        return movMiscExpenseRepository.getTotalAmountByMovement(id);
    }

    @Override
    public Double getTotalAmountByMiscExpense(Long id) {
        return movMiscExpenseRepository.getTotalAmountByMiscExpense(id);
    }
}
