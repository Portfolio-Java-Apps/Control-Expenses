package dev.gclopes.ControlExpensesData.Services.Implementation;

import dev.gclopes.ControlExpensesData.enumType.Type;
import dev.gclopes.ControlExpensesData.model.*;
import dev.gclopes.ControlExpensesData.repositories.MovPersonalGoodsRepository;
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
class MovPersonalGoodsServiceImplTest {
    @Mock()
    private MovPersonalGoodsRepository movPersonalGoodsRepository;
    private MovPersonalGoodsServiceImpl movPersonalGoodsService;
    Set<MovPersonalGoods> movPersonalGoods = new HashSet<>();
    MovPersonalGoodsID movPersonalGoodIDSelected;
    private final String[] ArrStrDesc = {"Mercearia", "Bebidas", "DP Higiene", "Lactic/Cong.", "Talho", "Peixaria",
            "Charcutaria", "Frutas e Legumes", "Bio e Saudável", "Padaria", "Take-Away",
            "Casa", "Outros", "Descontos"};
    Set<PersonalGoods> initPersonalGoods() {
        Set<PersonalGoods> personalGoods = new HashSet<>();

        long personalGoodsId=0;
        for(String s : ArrStrDesc)
        {
            PersonalGoods personalGood = new PersonalGoods(++personalGoodsId, s);
            personalGoods.add(personalGood);
        }
        return personalGoods;
    }

    @BeforeEach
    void setUp() {
        movPersonalGoodsService = new MovPersonalGoodsServiceImpl(movPersonalGoodsRepository);
        Set<PersonalGoods> personalGoods = initPersonalGoods();

        long nrOfRecordsMov = 100L;
        for(long indexMov = 1; indexMov <= nrOfRecordsMov; indexMov++) {
            Set<MovPersonalGoodsID> movPersonalGoodsIDs = new HashSet<>();
            Random rand = new Random();
            Movement movement = Movement
                    .builder()
                    .type(Type.DEBT)
                    .date(new Date())
                    .source(new Source(0, "SourceTest!"))
                    .typeOfPayment(new TypeOfPayment(0L, "TypeOfPaymentTest"))
                    .discount(0d)
                    .build();
            movement.setId(indexMov);
            long NrOfPersonalGoods = rand.nextInt(personalGoods.size())+1;
            System.out.printf("NrOfPersonalGoods: %d%n"
                    ,NrOfPersonalGoods);
            long startingPoint = rand.nextInt((int)(personalGoods.size()-NrOfPersonalGoods+1));
            System.out.printf("startingPoint: %d%n"
                    , startingPoint);
            double totalAmount = 0d;
            for(long indexMisc=startingPoint;
                indexMisc<(NrOfPersonalGoods+startingPoint) && indexMisc<=personalGoods.size();
                indexMisc++){
                long finalIndexMisc = indexMisc;
                PersonalGoods personalGoodsObj = personalGoods
                        .stream()
                        .filter(x-> Objects.equals(x.getId(), finalIndexMisc)).findFirst()
                        .orElse(null);
                if(personalGoodsObj==null)
                    continue;
                System.out.println(personalGoodsObj);
                MovPersonalGoods MovPersonalGoods = new MovPersonalGoods(new MovPersonalGoodsID(indexMov, personalGoodsObj.getId()));
                MovPersonalGoods.setPersonalGoods(personalGoodsObj);
                MovPersonalGoods.setMovement(movement);
                double amount = (double) round((rand.nextInt(100) + rand.nextDouble()) *100)/100;
                MovPersonalGoods.setAmount(amount);

                totalAmount += amount;
                if((movPersonalGoodIDSelected==null && rand.nextBoolean()) ||
                        (movPersonalGoodIDSelected==null && indexMov== nrOfRecordsMov) )
                {
                    movPersonalGoodIDSelected = MovPersonalGoods.getMovPersonalGoodsID();
                }
                movPersonalGoodsIDs.add(new MovPersonalGoodsID(indexMov, indexMisc));
                movPersonalGoods.add(MovPersonalGoods);
            }
            for (MovPersonalGoodsID mID:movPersonalGoodsIDs) {
                MovPersonalGoods temp = movPersonalGoods
                        .stream()
                        .filter(x-> x.getMovPersonalGoodsID().equals(mID))
                        .findFirst()
                        .orElse(null);
                if(temp!=null){
                    totalAmount = (double)round(totalAmount*100d)/100d;
                    movement.setTotalAmount(totalAmount);
                    movPersonalGoods.remove(temp);
                    temp.setMovement(movement);
                    movPersonalGoods.add(temp);
                }
            }
        }
        movPersonalGoods.forEach(x-> System.out.println(x.toString()));
        System.out.println(movPersonalGoodIDSelected);
    }
    @Test
    void findAll() {
        when(movPersonalGoodsRepository.findAll()).thenReturn(movPersonalGoods);
        assertNotNull(movPersonalGoodsService.findAll());
        verify(movPersonalGoodsRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        String strMessage = "Cannot insert a personal good in a movement using only one id.";
        NotSupportedException ex = assertThrows(NotSupportedException.class, () ->
                movPersonalGoodsService.findById(1L), strMessage);
        assertEquals(ex.getMessage(), strMessage);
    }

    MovPersonalGoods getOneMovPersonalGoods()
    {
        MovPersonalGoods movPersonalGoods;
        do {

            movPersonalGoods = this.movPersonalGoods.stream()
                    .filter(y -> Objects.equals(y.getMovPersonalGoodsID(), movPersonalGoodIDSelected))
                    .findFirst()
                    .orElse(null);
        }
        while(movPersonalGoods==null);
        return movPersonalGoods;
    }

    @Test
    void UpdateRecord() {
        MovPersonalGoods movPersonalGood=getOneMovPersonalGoods();

        Random rand = new Random();
        Double TotalAmount = (double) round((rand.nextInt(100) + rand.nextDouble()) *100)/100;

        movPersonalGood.setAmount(TotalAmount);
        when(movPersonalGoodsRepository.save(movPersonalGood)).thenReturn(movPersonalGood);
        movPersonalGoodsService.save(movPersonalGood);
        verify(movPersonalGoodsRepository, times(1)).save(movPersonalGood);
    }

    @Test
    void CreateNewRecord() {

        MovPersonalGoods movPersonalGood = new MovPersonalGoods();
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
            MovPersonalGoodsID movPersonalGoodID = new MovPersonalGoodsID();
            movPersonalGoodID.setPersonalGoodId(indexMisc);
            movPersonalGoodID.setMovementId(nrOfRecordsMisc +1);
            movPersonalGood.setMovPersonalGoodsID(movPersonalGoodID);
            PersonalGoods PersonalGood = new PersonalGoods(indexMisc, "Doing a test!");
            movPersonalGood.setMovPersonalGoodsID(movPersonalGoodID);
            movPersonalGood.setPersonalGoods(PersonalGood);
            movPersonalGood.setMovement(movement);
            Random rand = new Random();
            Double TotalAmount = (double) round((rand.nextInt(100) + rand.nextDouble()) *100)/100;
            movPersonalGood.setAmount(TotalAmount);
            movPersonalGoods.add(movPersonalGood);
        }
        when(movPersonalGoodsRepository.save(movPersonalGood)).thenReturn(movPersonalGood);
        movPersonalGoodsService.save(movPersonalGood);
        verify(movPersonalGoodsRepository, times(1)).save(movPersonalGood);
    }

    @Test
    void delete() {
        MovPersonalGoods movPersonalGoods=getOneMovPersonalGoods();
        movPersonalGoodsService.delete(movPersonalGoods);
        verify(movPersonalGoodsRepository, times(1)).delete(movPersonalGoods);
    }

    @Test
    void deleteByMovementIdAndPersonalGoodsId() {
        MovPersonalGoods movPersonalGood=getOneMovPersonalGoods();
        movPersonalGoodsService.deleteByMovementIdAndPersonalGoodsId(movPersonalGood.getMovPersonalGoodsID().getMovementId(),
                movPersonalGood.getMovPersonalGoodsID().getPersonalGoodId());
        verify(movPersonalGoodsRepository, times(1))
                .deleteByMovementIdAndPersonalGoodsId(movPersonalGood.getMovPersonalGoodsID().getMovementId(),
                        movPersonalGood.getMovPersonalGoodsID().getPersonalGoodId());
    }

    @Test
    void findAllByMovementId() {
        Set<MovPersonalGoods> movPersonalGoods = new HashSet<>();
        long id = 64L;
        this.movPersonalGoods.stream()
                .filter(x-> Objects.equals(x.getMovPersonalGoodsID().getMovementId(), id)).forEach(movPersonalGoods::add);
        when(movPersonalGoodsRepository.findAllByMovementId(id)).thenReturn(movPersonalGoods);

        assertNotNull(movPersonalGoodsService.findAllByMovementId(id));
        //verify(movPersonalGoodRepository, times(1)).findAllByIdMovementId(id);
    }

    @Test
    void findAllByPersonalGoodsId() {
        Set<MovPersonalGoods> movPersonalGoods = new HashSet<>();
        Long id = 4L;
        this.movPersonalGoods.stream()
                .filter(x-> Objects.equals(x.getMovPersonalGoodsID().getPersonalGoodId(), id))
                .forEach(movPersonalGoods::add);
        when(movPersonalGoodsRepository.findAllByPersonalGoodsId(id)).thenReturn(movPersonalGoods);

        assertNotNull(movPersonalGoodsService.findAllByPersonalGoodsId(id));
//        verify(movPersonalGoodRepository, times(1)).findAllByIdPersonalGoodId(id);
    }

    @Test
    void getTotalAmountByMovement() {
        long id = 58L;
        Double sum = movPersonalGoods.stream()
                .filter(x-> Objects.equals(x.getMovPersonalGoodsID().getMovementId(), id))
                .mapToDouble(MovPersonalGoods::getAmount)
                .sum();
        when(movPersonalGoodsRepository.getTotalAmountByMovement(id)).thenReturn(sum);
        assertNotEquals(0, movPersonalGoodsService.getTotalAmountByMovement(id));
        verify(movPersonalGoodsRepository, times(1)).getTotalAmountByMovement(id);
    }

    @Test
    void getTotalAmountByPersonalGoods() {
        Long id = movPersonalGoodIDSelected.getPersonalGoodId();
        Double sum = movPersonalGoods.stream()
                .filter(x-> Objects.equals(x.getMovPersonalGoodsID().getPersonalGoodId(), id))
                .mapToDouble(MovPersonalGoods::getAmount)
                .sum();
        when(movPersonalGoodsRepository.getTotalAmountByPersonalGoods(id)).thenReturn(sum);
        assertNotEquals(0, movPersonalGoodsService.getTotalAmountByPersonalGoods(id));
        verify(movPersonalGoodsRepository, times(1)).getTotalAmountByPersonalGoods(id);
    }

    @Test
    void deleteById() {
        String strMessage = "Cannot delete a personal good in a movement using only one id.";
        NotSupportedException ex = assertThrows(NotSupportedException.class, () ->
                movPersonalGoodsService.deleteById(1L), strMessage);
        assertEquals(ex.getMessage(), strMessage);
    }
}