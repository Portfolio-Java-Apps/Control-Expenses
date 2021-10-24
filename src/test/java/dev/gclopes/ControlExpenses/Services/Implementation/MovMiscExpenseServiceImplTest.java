package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.enumType.Type;
import dev.gclopes.ControlExpenses.model.*;
import dev.gclopes.ControlExpenses.repositories.MovMiscExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static java.lang.Math.round;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovMiscExpenseServiceImplTest {
    @Mock()
    private MovMiscExpenseRepository movMiscExpenseRepository;
    private MovMiscExpenseServiceImpl movMiscExpenseService;
    private final long nrOfRecordsMov = 100L;
    private final long nrOfRecordsMisc = 12L;
    Set<MovMiscExpense> movMiscExpenses = new HashSet<>();
    MovMiscExpenseID movMiscExpenseIDSelected;
    @BeforeEach
    void setUp() {
        movMiscExpenseService = new MovMiscExpenseServiceImpl(movMiscExpenseRepository);


        for(long indexMov = 1; indexMov <= nrOfRecordsMov; indexMov++)
        {
            Random rand = new Random();
            Movement movement =  Movement
                    .builder()
                    .type(Type.DEBT)
                    .date(new Date())
                    .source(new Source(0, "SourceTest!"))
                    .typeOfPayment(new TypeOfPayment(0L, "TypeOfPaymentTest"))
                    .build();
            movement.setId(indexMov);

            MovMiscExpense movMiscExpense = new MovMiscExpense(new MovMiscExpenseID(indexMov, 1L));
            MiscExpense miscExpense = new MiscExpense(1L, "Testing!");
            movMiscExpense.setMiscExpense(miscExpense);
            movMiscExpense.setMovement(movement);
            Double TotalAmount = (double) round((rand.nextInt(100) + rand.nextDouble()) *100)/100;
            movMiscExpense.setAmount(TotalAmount);

            if((movMiscExpenseIDSelected==null && rand.nextBoolean()) ||
                    (movMiscExpenseIDSelected==null && indexMov==nrOfRecordsMov) )
            {
                movMiscExpenseIDSelected = movMiscExpense.getMovMiscExpenseID();
            }
            movMiscExpenses.add(movMiscExpense);
        }
        System.out.println(movMiscExpenseIDSelected.toString() );
    }

    @Test
    void findAll() {
        when(movMiscExpenseRepository.findAll()).thenReturn(movMiscExpenses);
        assertNotNull(movMiscExpenseService.findAll());
        verify(movMiscExpenseRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        assertNull(movMiscExpenseService.findById(1L));
    }

    MovMiscExpense getOneMovMiscExpense()
    {
        MovMiscExpense movMiscExpense;
        do {

            movMiscExpense = movMiscExpenses.stream()
                    .filter(y -> Objects.equals(y.getMovMiscExpenseID(), movMiscExpenseIDSelected))
                    .findFirst()
                    .orElse(null);
        }
        while(movMiscExpense==null);
        return movMiscExpense;
    }
    @Test
    void UpdateRecord() {
        MovMiscExpense movMiscExpense=getOneMovMiscExpense();

        Random rand = new Random();
        Double TotalAmount = (double) round((rand.nextInt(100) + rand.nextDouble()) *100)/100;

        movMiscExpense.setAmount(TotalAmount);
        when(movMiscExpenseRepository.save(movMiscExpense)).thenReturn(movMiscExpense);
        movMiscExpenseService.save(movMiscExpense);
        verify(movMiscExpenseRepository, times(1)).save(movMiscExpense);
    }

    @Test
    void CreateNewRecord() {

        MovMiscExpense movMiscExpense = new MovMiscExpense();
        Movement movement =  Movement
                .builder()
                .type(Type.DEBT)
                .date(new Date())
                .source(new Source(0, "SourceTest!"))
                .typeOfPayment(new TypeOfPayment(0L, "TypeOfPaymentTest"))
                .build();
        movement.setId(nrOfRecordsMisc+1);
        for(long indexMisc = 1; indexMisc<= nrOfRecordsMisc; indexMisc++)
        {
            MovMiscExpenseID movMiscExpenseID = new MovMiscExpenseID();
            movMiscExpenseID.setMiscExpenseId(indexMisc);
            movMiscExpenseID.setMovementId(nrOfRecordsMisc+1);
            movMiscExpense.setMovMiscExpenseID(movMiscExpenseID);
            MiscExpense miscExpense = new MiscExpense(indexMisc, "Doing a test!");
            movMiscExpense.setMovMiscExpenseID(movMiscExpenseID);
            movMiscExpense.setMiscExpense(miscExpense);
            movMiscExpense.setMovement(movement);
            Random rand = new Random();
            Double TotalAmount = (double) round((rand.nextInt(100) + rand.nextDouble()) *100)/100;
            movMiscExpense.setAmount(TotalAmount);
            movMiscExpenses.add(movMiscExpense);
        }
        when(movMiscExpenseRepository.save(movMiscExpense)).thenReturn(movMiscExpense);
        movMiscExpenseService.save(movMiscExpense);
        verify(movMiscExpenseRepository, times(1)).save(movMiscExpense);
    }

    @Test
    void delete() {
        MovMiscExpense movMiscExpense=getOneMovMiscExpense();
        movMiscExpenseService.delete(movMiscExpense);
        verify(movMiscExpenseRepository, times(1)).delete(movMiscExpense);
    }

    @Test
    void deleteByMovementIdAndMiscExpenseId() {
        MovMiscExpense movMiscExpense=getOneMovMiscExpense();
        movMiscExpenseService.deleteByMovementIdAndMiscExpenseId(movMiscExpense.getMovMiscExpenseID().getMovementId(),
                movMiscExpense.getMovMiscExpenseID().getMiscExpenseId());
        verify(movMiscExpenseRepository, times(1))
                .deleteByMovementIdAndMiscExpenseId(movMiscExpense.getMovMiscExpenseID().getMovementId(),
                                                    movMiscExpense.getMovMiscExpenseID().getMiscExpenseId());
    }

    @Test
    void findAllByMovementId() {
        Set<MovMiscExpense> movMiscExpenses = new HashSet<>();
        long id = movMiscExpenseIDSelected.getMovementId();
        MovMiscExpenseID movMiscExpenseID = new MovMiscExpenseID(35L, 1L);
        this.movMiscExpenses.stream()
                .filter(x-> Objects.equals(x.getMovMiscExpenseID(), movMiscExpenseID)).forEach(movMiscExpenses::add);
        when(movMiscExpenseRepository.findAllByMovementId(id)).thenReturn(movMiscExpenses);

        assertNotNull(movMiscExpenseService.findAllByMovementId(id));
        //verify(movMiscExpenseRepository, times(1)).findAllByIdMovementId(id);
    }

    @Test
    void findAllByMiscExpenseId() {
        Set<MovMiscExpense> movMiscExpenses = new HashSet<>();
        Long id = movMiscExpenseIDSelected.getMovementId();
        this.movMiscExpenses.stream()
                .filter(x-> Objects.equals(x.getMovMiscExpenseID().getMiscExpenseId(), 1L))
                .forEach(movMiscExpenses::add);
        when(movMiscExpenseRepository.findAllByMiscExpenseId(id)).thenReturn(movMiscExpenses);

        assertNotNull(movMiscExpenseService.findAllByMiscExpenseId(id));
//        verify(movMiscExpenseRepository, times(1)).findAllByIdMiscExpenseId(id);
    }

    @Test
    void getTotalAmountByMovement() {
        long id = movMiscExpenseIDSelected.getMovementId();
        Double sum = movMiscExpenses.stream()
                .filter(x-> Objects.equals(x.getMovMiscExpenseID().getMovementId(), id))
                .mapToDouble(MovMiscExpense::getAmount)
                .sum();
        when(movMiscExpenseRepository.getTotalAmountByMovement(id)).thenReturn(sum);
        assertNotEquals(0, movMiscExpenseService.getTotalAmountByMovement(id));
        verify(movMiscExpenseRepository, times(1)).getTotalAmountByMovement(id);
    }

    @Test
    void getTotalAmountByMiscExpense() {
        Long id = movMiscExpenseIDSelected.getMiscExpenseId();
        Double sum = movMiscExpenses.stream()
                .filter(x-> Objects.equals(x.getMovMiscExpenseID().getMiscExpenseId(), id))
                .mapToDouble(MovMiscExpense::getAmount)
                .sum();
        when(movMiscExpenseRepository.getTotalAmountByMiscExpense(id)).thenReturn(sum);
        assertNotEquals(0, movMiscExpenseService.getTotalAmountByMiscExpense(id));
        verify(movMiscExpenseRepository, times(1)).getTotalAmountByMiscExpense(id);
    }

}