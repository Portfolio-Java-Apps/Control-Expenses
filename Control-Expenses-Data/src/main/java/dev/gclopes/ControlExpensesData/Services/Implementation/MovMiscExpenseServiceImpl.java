package dev.gclopes.ControlExpensesData.Services.Implementation;

import dev.gclopes.ControlExpensesData.Services.MovMiscExpenseService;
import dev.gclopes.ControlExpensesData.model.MovMiscExpense;
import dev.gclopes.ControlExpensesData.repositories.MovMiscExpenseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.NotSupportedException;
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
    public MovMiscExpense findById(Long aLong) throws NotSupportedException {
        throw new NotSupportedException("Cannot insert a miscellaneous expense in a movement using only one id.");
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
    public void deleteById(Long aLong) throws NotSupportedException {
        throw new NotSupportedException("Cannot delete a miscellaneous expense in a movement using only one id.");
    }

    public void deleteByMovementIdAndMiscExpenseId(Long movementId, Long miscExpenseId) {
        movMiscExpenseRepository.deleteByMovementIdAndMiscExpenseId(movementId, miscExpenseId);
    }

    @Override
    public Set<MovMiscExpense> findAllByMovementId(Long id) {
        return movMiscExpenseRepository.findAllByMovementId(id);
    }

    @Override
    public Set<MovMiscExpense> findAllByMiscExpenseId(Long id) {
        return movMiscExpenseRepository.findAllByMiscExpenseId(id);
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
