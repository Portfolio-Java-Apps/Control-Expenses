package dev.gclopes.ControlExpensesData.Services.Implementation;

import dev.gclopes.ControlExpensesData.Services.MiscExpenseService;
import dev.gclopes.ControlExpensesData.model.MiscExpense;
import dev.gclopes.ControlExpensesData.repositories.MiscExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("MiscExpenseService")
public class MiscExpenseServiceImpl implements MiscExpenseService {

    private final MiscExpenseRepository miscExpenseRepository;

    public MiscExpenseServiceImpl(MiscExpenseRepository miscExpenseRepository) {
        this.miscExpenseRepository = miscExpenseRepository;
    }


    @Override
    public MiscExpense findByName(String name) {
        return miscExpenseRepository.findByName(name).orElse(null);
    }

    @Override
    public Set<MiscExpense> findAll() {
        Set<MiscExpense> miscExpenses = new HashSet<>();
        miscExpenseRepository.findAll().forEach(miscExpenses::add);
        return miscExpenses;
    }

    @Override
    public MiscExpense findById(Long aLong) {
        return miscExpenseRepository.findById(aLong).orElse(null);
    }

    @Override
    public MiscExpense save(MiscExpense object) {
        return miscExpenseRepository.save(object);
    }

    @Override
    public void delete(MiscExpense object) {
        miscExpenseRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        miscExpenseRepository.deleteById(aLong);
    }
}
