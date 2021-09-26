package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.model.MiscExpense;
import dev.gclopes.ControlExpenses.repositories.MiscExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static java.lang.Math.random;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    void findByName() {
        int id = rand.nextInt(miscExpenses.size());
        String strName =  ArrStrDesc[id];
        MiscExpense myMiscExpense = miscExpenses.stream().filter(x-> Objects.equals(x.getName(),strName)).findFirst().orElse(null);
        when(miscExpenseRepository.findByName(strName)).thenReturn(Optional.ofNullable(myMiscExpense));
        miscExpenseService.findByName(strName);
        verify(miscExpenseRepository, times(1)).findByName(strName);
    }

    @Test
    void findAll() {
        when(miscExpenseRepository.findAll()).thenReturn(miscExpenses);
        assertNotNull(miscExpenseService.findAll());
        verify(miscExpenseRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(miscExpenseRepository.findById(id)).thenReturn(miscExpense!=null ? Optional.of(miscExpense) : Optional.empty());
        assertNotNull(miscExpenseService.findById(id));
        when(miscExpenseRepository.findById(id)).thenReturn(Optional.empty());
        assertNull(miscExpenseService.findById(id));
        verify(miscExpenseRepository, times(2)).findById(id);

    }

    @Test
    void save() {
        assertNotNull(miscExpense);
        when(miscExpenseRepository.save(miscExpense)).thenReturn(miscExpense);
        miscExpenseService.save(miscExpense);
        verify(miscExpenseRepository, times(1)).save(miscExpense);
    }

    @Test
    void delete() {
        miscExpenseService.delete(miscExpense);
        verify(miscExpenseRepository, times(1)).delete(miscExpense);
    }

    @Test
    void deleteById() {
        miscExpenseService.deleteById(id);
        verify(miscExpenseRepository, times(1)).deleteById(id);
    }
}