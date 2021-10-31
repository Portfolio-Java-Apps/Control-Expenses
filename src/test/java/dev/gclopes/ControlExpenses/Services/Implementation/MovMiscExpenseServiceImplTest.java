package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.enumType.Type;
import dev.gclopes.ControlExpenses.model.*;
import dev.gclopes.ControlExpenses.repositories.MovMiscExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.NotSupportedException;
import java.util.*;

import static java.lang.Math.round;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovMiscExpenseServiceImplTest {
    @Mock()
    private MovMiscExpenseRepository movMiscExpenseRepository;
    private MovMiscExpenseServiceImpl movMiscExpenseService;
    Set<MovMiscExpense> movMiscExpenses = new HashSet<>();
    MovMiscExpenseID movMiscExpenseIDSelected;
    private final String[] ArrStrDesc = {"Saúde", "Cartão Crédito", "Contrib/Impostos", "Condomínios", "Serviços", "Viatura", "Outros", "Atm"};

    @BeforeEach
    void setUp() {
        movMiscExpenseService = new MovMiscExpenseServiceImpl(movMiscExpenseRepository);
        Set<MiscExpense> miscExpenses = new HashSet<>();

        long miscExpenseId=0;
        for(String s : ArrStrDesc)
        {
            MiscExpense miscExpense = new MiscExpense(++miscExpenseId, s);
            miscExpenses.add(miscExpense);
        }

        long nrOfRecordsMov = 100L;
        for(long indexMov = 1; indexMov <= nrOfRecordsMov; indexMov++)
        {
            Set<MovMiscExpenseID> movMiscExpenseIDs = new HashSet<>();
            Random rand = new Random();
            Movement movement =  Movement
                    .builder()
                    .type(Type.DEBT)
                    .date(new Date())
                    .source(new Source(0, "SourceTest!"))
                    .typeOfPayment(new TypeOfPayment(0L, "TypeOfPaymentTest"))
                    .discount(0d)
                    .build();
            movement.setId(indexMov);

            long NrOfMiscExpenses = rand.nextInt(miscExpenses.size())+1;
            System.out.printf("NrOfMiscExpenses: %d%n"
                    ,NrOfMiscExpenses);
            long startingPoint = rand.nextInt((int)(miscExpenses.size()-NrOfMiscExpenses+1));
            System.out.printf("startingPoint: %d%n"
                    , startingPoint);
            double totalAmount = 0d;
            for(long indexMisc=startingPoint;
                indexMisc<(NrOfMiscExpenses+startingPoint) && indexMisc<=miscExpenses.size();
                indexMisc++){
                long finalIndexMisc = indexMisc;
                MiscExpense miscExpense = miscExpenses
                        .stream()
                        .filter(x-> Objects.equals(x.getId(), finalIndexMisc)).findFirst()
                        .orElse(null);
                if(miscExpense==null)
                    continue;
                System.out.println(miscExpense);
                MovMiscExpense movMiscExpense = new MovMiscExpense(new MovMiscExpenseID(indexMov, miscExpense.getId()));
                movMiscExpense.setMiscExpense(miscExpense);
                movMiscExpense.setMovement(movement);
                double amount = (double) round((rand.nextInt(100) + rand.nextDouble()) *100)/100;
                movMiscExpense.setAmount(amount);

                totalAmount += amount;
                if((movMiscExpenseIDSelected==null && rand.nextBoolean()) ||
                        (movMiscExpenseIDSelected==null && indexMov== nrOfRecordsMov) )
                {
                    movMiscExpenseIDSelected = movMiscExpense.getMovMiscExpenseID();
                }
                movMiscExpenseIDs.add(new MovMiscExpenseID(indexMov, indexMisc));
                movMiscExpenses.add(movMiscExpense);
            }
            for (MovMiscExpenseID mID:movMiscExpenseIDs) {
                MovMiscExpense temp = movMiscExpenses
                        .stream()
                        .filter(x-> x.getMovMiscExpenseID().equals(mID))
                        .findFirst()
                        .orElse(null);
                if(temp!=null){
                    totalAmount = (double)round(totalAmount*100d)/100d;
                    movement.setTotalAmount(totalAmount);
                    movMiscExpenses.remove(temp);
                    temp.setMovement(movement);
                    movMiscExpenses.add(temp);
                }
            }
        }
        movMiscExpenses.forEach(x-> System.out.println(x.toString()));
        System.out.println(movMiscExpenseIDSelected);
    }

    @Test
    void findAll() {
        when(movMiscExpenseRepository.findAll()).thenReturn(movMiscExpenses);
        assertNotNull(movMiscExpenseService.findAll());
        verify(movMiscExpenseRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        String strMessage = "Cannot insert a miscellaneous expense in a movement using only one id.";
        NotSupportedException ex = assertThrows(NotSupportedException.class, () ->
                movMiscExpenseService.findById(1L), strMessage);
        assertEquals(ex.getMessage(), strMessage);
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
        long nrOfRecordsMisc = 12L;
        movement.setId(nrOfRecordsMisc +1);
        for(long indexMisc = 1; indexMisc<= nrOfRecordsMisc; indexMisc++)
        {
            MovMiscExpenseID movMiscExpenseID = new MovMiscExpenseID();
            movMiscExpenseID.setMiscExpenseId(indexMisc);
            movMiscExpenseID.setMovementId(nrOfRecordsMisc +1);
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
        long id = 64L;
        this.movMiscExpenses.stream()
                .filter(x-> Objects.equals(x.getMovMiscExpenseID().getMovementId(), id)).forEach(movMiscExpenses::add);
        when(movMiscExpenseRepository.findAllByMovementId(id)).thenReturn(movMiscExpenses);
        assertNotNull(movMiscExpenseService.findAllByMovementId(id));
    }

    @Test
    void findAllByMiscExpenseId() {
        Set<MovMiscExpense> movMiscExpenses = new HashSet<>();
        Long id = 4L;
        this.movMiscExpenses.stream()
                .filter(x-> Objects.equals(x.getMovMiscExpenseID().getMiscExpenseId(), id))
                .forEach(movMiscExpenses::add);
        when(movMiscExpenseRepository.findAllByMiscExpenseId(id)).thenReturn(movMiscExpenses);

        assertNotNull(movMiscExpenseService.findAllByMiscExpenseId(id));
    }

    @Test
    void getTotalAmountByMovement() {
        long id = 58L;
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

    @Test
    void deleteById() {
        String strMessage = "Cannot delete a miscellaneous expense in a movement using only one id.";
        NotSupportedException ex = assertThrows(NotSupportedException.class, () ->
                        movMiscExpenseService.deleteById(1L)
        , strMessage);
        assertEquals(ex.getMessage(), strMessage);
    }
}