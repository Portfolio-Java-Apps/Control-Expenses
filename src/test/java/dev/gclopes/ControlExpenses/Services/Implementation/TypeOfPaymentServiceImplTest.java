package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.enumType.Type;
import dev.gclopes.ControlExpenses.model.Movement;
import dev.gclopes.ControlExpenses.model.TypeOfPayment;
import dev.gclopes.ControlExpenses.repositories.TypeOfPaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static java.lang.Math.random;
import static java.lang.Math.round;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TypeOfPaymentServiceImplTest {

    @Mock()
    private TypeOfPaymentRepository typeOfPaymentRepository;

    private TypeOfPaymentServiceImpl typeOfPaymentService;

    private Set<TypeOfPayment> TypeOfPayments;
    private final String[] ArrStrDesc = { "Cash", "Cartão Continente", "Cartão Visa", "Cartão Crédito", "Cartão Menu"};

    private long id;
    private TypeOfPayment typeOfPayment;
    private Random rand;
    @BeforeEach
    void setUp() {
        typeOfPaymentService = new TypeOfPaymentServiceImpl(typeOfPaymentRepository);
        TypeOfPayments = new HashSet<>();

        long typeOfPaymentId=0;
        for(String s : ArrStrDesc)
        {
            TypeOfPayment typeOfPayment = new TypeOfPayment(++typeOfPaymentId, s);
            TypeOfPayments.add(typeOfPayment);
        }

        rand=new Random();
        id = (long) (random() * ((long) TypeOfPayments.size() -1) + 1);
        typeOfPayment = TypeOfPayments.stream().filter(x-> Objects.equals(x.id, id)).findFirst().orElse(null);

    }

    @Test
    void findAll() {
        when(typeOfPaymentRepository.findAll()).thenReturn(TypeOfPayments);
        assertNotNull(typeOfPaymentService.findAll());
        verify(typeOfPaymentRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(typeOfPaymentRepository.findById(id)).thenReturn(typeOfPayment !=null ? Optional.of(typeOfPayment) : Optional.empty());
        assertNotNull(typeOfPaymentService.findById(id));
        when(typeOfPaymentRepository.findById(id)).thenReturn(Optional.empty());
        assertNull(typeOfPaymentService.findById(id));
        verify(typeOfPaymentRepository, times(2)).findById(id);
    }

    @Test
    void save() {
        assertNotNull(typeOfPayment);

        Set<Movement> movements = new HashSet<>();
        for(int i=0; i<5; i++)
        {
            Double TotalAmount = (double) round((rand.nextInt(100) + rand.nextDouble()) *100)/100;
            double TotalDiscount = 0.00;
            if(rand.nextBoolean())
                TotalDiscount = (double) round((rand.nextInt(10) + rand.nextDouble()) *100)/100;
            Movement movement = Movement
                    .builder()
                    .type(Type.DEBT)
                    .date(new Date())
                    .typeOfPayment(typeOfPayment)
                    .discount(TotalDiscount)
                    .totalAmount(TotalAmount)
                    .build();
            movement.setId((long) i+1);
            System.out.println(movement);
            movements.add(movement);
        }
        typeOfPayment.setMovements(movements);
        when(typeOfPaymentRepository.save(typeOfPayment)).thenReturn(typeOfPayment);
        typeOfPaymentService.save(typeOfPayment);
        verify(typeOfPaymentRepository, times(1)).save(typeOfPayment);
    }

    @Test
    void delete() {
        typeOfPaymentService.delete(typeOfPayment);
        verify(typeOfPaymentRepository, times(1)).delete(typeOfPayment);
    }

    @Test
    void deleteById() {
        typeOfPaymentService.deleteById(id);
        verify(typeOfPaymentRepository, times(1)).deleteById(id);
    }

    @Test
    void findByName() {
        int id = rand.nextInt(ArrStrDesc.length);
        String strName =  ArrStrDesc[id];
        TypeOfPayment myTypeOfPayment = TypeOfPayments.stream().filter(x-> Objects.equals(x.getName(),strName)).findFirst().orElse(null);
        when(typeOfPaymentRepository.findByName(strName)).thenReturn(Optional.ofNullable(myTypeOfPayment));
        typeOfPaymentService.findByName(strName);
        verify(typeOfPaymentRepository, times(1)).findByName(strName);
    }
}