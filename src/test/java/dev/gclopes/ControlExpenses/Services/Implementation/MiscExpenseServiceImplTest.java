package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.model.MiscExpense;
import dev.gclopes.ControlExpenses.repositories.MiscExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import static java.lang.Math.random;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MiscExpenseServiceImplTest {

    @Mock()
    private MiscExpenseRepository miscExpenseRepository;
    private MiscExpenseServiceImpl miscExpenseService;

    private MiscExpense miscExpense;
    private Set<MiscExpense> miscExpenses;
    private long id;
    private Random rand;
    private final String[] ArrStrDesc = {"Saúde", "Cartão Crédito", "Contrib/Impostos", "Condomínios", "Serviços", "Viatura", "Outros", "Atm"};
    @BeforeEach
    void setUp() {
        miscExpenseService = new MiscExpenseServiceImpl(miscExpenseRepository);
        miscExpenses = new HashSet<>();
        long miscExpenseId=0;
        for(String s : ArrStrDesc)
        {
            MiscExpense miscExpense = new MiscExpense(++miscExpenseId, s);
            miscExpenses.add(miscExpense);
        }
        rand=new Random();
        id = (long) (random() * ((long) miscExpenses.size() -1) + 1);
        miscExpense = miscExpenses.stream().filter(x-> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    @Test
    void findByDesc() {
    }

    @Test
    void findAll() {
        when(miscExpenseRepository.findAll()).thenReturn(miscExpenses);
        assertNotNull(miscExpenseService.findAll());
        verify(miscExpenseRepository, times(1)).findAll();
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}