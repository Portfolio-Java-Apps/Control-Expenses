package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.enumType.Type;
import dev.gclopes.ControlExpenses.model.Movement;
import dev.gclopes.ControlExpenses.model.Source;
import dev.gclopes.ControlExpenses.model.TypeOfPayment;
import dev.gclopes.ControlExpenses.repositories.MovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.lang.Math.random;
import static java.lang.Math.round;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovementServiceImplTest {
    @Mock()
    private MovementRepository movementRepository;

    private MovementServiceImpl movementService;

    private Set<Movement> movements = new HashSet<>();
    private Set<TypeOfPayment> TypeOfPayments;
    private final String[] ArrStrPayments = { "Cash", "Cartão Continente", "Cartão Visa", "Cartão Crédito",
                                              "Cartão Menu"};

    private List<Long> idsPayment;

    private Set<Source> sources;
    private final String[] ArrStrSource = { "Continente", "Jumbo", "Leroy Merlin", "Lusiadas/Nuno", "Praça/Alfragide",
                                            "Praça/Buraca", "Celeiro", "Conta/Manutenção"};


    private List<Long> idsSource;
    private Movement movement;
    String string = "January 2, 2010";
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    private long id;
    private Date dateToSearch;
    private static Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

    private void createSourceList() {
        sources = new HashSet<>();
        idsSource = new ArrayList<>();
        long sourceId=0L;
        for(String s : ArrStrSource) {
            Source source = new Source(++sourceId, s);
            sources.add(source);
            idsSource.add(sourceId);
        }
    }

    private void createTypeOfPaymentList() {
        TypeOfPayments = new HashSet<>();
        idsPayment = new ArrayList<>();
        long typeOfPaymentId=0;
        for(String s : ArrStrPayments)
        {
            TypeOfPayment typeOfPayment = new TypeOfPayment(++typeOfPaymentId, s);
            TypeOfPayments.add(typeOfPayment);
            idsPayment.add(typeOfPaymentId);
        }
    }

    private Movement getOneMovement(long id) throws ParseException {
        Source source;
        Random rand = new Random();
        int indArrPayment =  (int) (random() * (idsPayment.size() -1) + 1);
        TypeOfPayment typeOfPayment = TypeOfPayments.stream()
                .filter(x-> Objects.equals(x.id, idsPayment.get(indArrPayment)))
                .findFirst()
                .orElse(null);

        int indArrSource =  (int) (random() * (idsSource.size() -1) + 1);
        Date date = between(format.parse("2021-01-01"), new Date());
        if(rand.nextBoolean() || dateToSearch==null)
            dateToSearch = date;

        source = sources.stream()
                .filter(x-> Objects.equals(x.id, idsSource.get(indArrSource)))
                .findFirst()
                .orElse(null);

        Double TotalAmount = (double) round((rand.nextInt(100) + rand.nextDouble()) *100)/100;

        Double TotalDiscount = (double) round((rand.nextInt(10) + rand.nextDouble()) *100)/100;


        Movement movement = Movement
                .builder()
                .type(Type.DEBT)
                .date(date)
                .source(source)
                .discount(TotalDiscount)
                .totalAmount(TotalAmount)
                .typeOfPayment(typeOfPayment)
                .build();
        movement.setId(id);
        return movement;
    }
    @BeforeEach
    void setUp() throws ParseException {
        movementService = new MovementServiceImpl(movementRepository);

        createSourceList();
        createTypeOfPaymentList();
        dateToSearch = null;
        movements = new HashSet<>();
        for(int i=0; i<5; i++)
        {
            movement=getOneMovement(i+1);
            System.out.println(movement);
            movements.add(movement);
        }

        id = (long) (random() * ((long) movements.size() -1) + 1);
        movement = movements.stream().filter(x-> Objects.equals(x.id, id)).findFirst().orElse(null);
    }

    @Test
    void findAll() {
        when(movementRepository.findAll()).thenReturn(movements);
        assertNotNull(movementService.findAll());
        verify(movementRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(movementRepository.findById(id)).thenReturn(movement !=null ? Optional.of(movement) : Optional.empty());
        assertNotNull(movementService.findById(id));
        when(movementRepository.findById(id)).thenReturn(Optional.empty());
        assertNull(movementService.findById(id));
        verify(movementRepository, times(2)).findById(id);
    }

    @Test
    void save() throws ParseException {
        assertNotNull(movements);
        assertNotEquals(0, movements.size());
        movement = getOneMovement(1);

        when(movementRepository.save(movement)).thenReturn(movement);
        movementService.save(movement);
        verify(movementRepository, times(1)).save(movement);
        Optional<Movement> temp = movements.stream().max(Comparator.comparing(Movement::getId));

        assertTrue(temp.isPresent());

        movement = getOneMovement(temp.get().getId()+1);
        when(movementRepository.save(movement)).thenReturn(movement);
        movementService.save(movement);
        verify(movementRepository, times(1)).save(movement);

    }

    @Test
    void delete() {
        movementService.delete(movement);
        verify(movementRepository, times(1)).delete(movement);
    }

    @Test
    void deleteById() {
        movementService.deleteById(id);
        verify(movementRepository, times(1)).deleteById(id);
    }

    @Test
    void findByType() {
        Set<Movement> myMovements = movements.stream().filter(x-> Objects.equals(x.getType(),Type.DEBT))
                .collect(Collectors.toSet());
        when(movementRepository.findByType(Type.DEBT)).thenReturn(myMovements);
        assertNotNull(movementService.findByType(Type.DEBT));
        verify(movementRepository, times(1)).findByType(Type.DEBT);
    }

    @Test
    void findByDate() {
        Set<Movement> myMovements = movements.stream().filter(x-> Objects.equals(x.getDate(),dateToSearch))
                .collect(Collectors.toSet());
        when(movementRepository.findByDate(dateToSearch)).thenReturn(myMovements);
        assertNotNull(movementService.findByDate(dateToSearch));
        verify(movementRepository, times(1)).findByDate(dateToSearch);
    }

    @Test
    void getTotalAmountByDate() {
        Double sum = movements.stream()
                .filter(o->o.getDate().equals(dateToSearch))
                .mapToDouble(Movement::getTotalAmount)
                .sum();
        when(movementRepository.getTotalAmountByDate(dateToSearch)).thenReturn(sum);
        assertNotEquals(0, movementService.getTotalAmountByDate(dateToSearch));
        verify(movementRepository, times(1)).getTotalAmountByDate(dateToSearch);
    }

    @Test
    void getTotalAmountByMonth() {
        Month month = dateToSearch.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        Double sum = movements.stream()
                .filter(o-> o.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth().equals(month))
                .mapToDouble(Movement::getTotalAmount)
                .sum();
        when(movementRepository.getTotalAmountByMonth(month.ordinal())).thenReturn(sum);
        assertNotEquals(0, movementService.getTotalAmountByMonth(month.ordinal()));
        verify(movementRepository, times(1)).getTotalAmountByMonth(month.ordinal());
    }

    @Test
    void getTotalAmountByYear() {
        int year = dateToSearch.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        Double sum = movements.stream()
                .filter(o-> o.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()==year)
                .mapToDouble(Movement::getTotalAmount)
                .sum();
        when(movementRepository.getTotalAmountByYear(year)).thenReturn(sum);
        assertNotEquals(0, movementService.getTotalAmountByYear(year));
        verify(movementRepository, times(1)).getTotalAmountByYear(year);
    }

    @Test
    void getTotalDiscountByDate() {
        Double sum = movements.stream()
                .filter(o->o.getDate().equals(dateToSearch))
                .mapToDouble(Movement::getDiscount)
                .sum();
        when(movementRepository.getTotalDiscountByDate(dateToSearch)).thenReturn(sum);
        assertNotEquals(0, movementService.getTotalDiscountByDate(dateToSearch));
        verify(movementRepository, times(1)).getTotalDiscountByDate(dateToSearch);
    }

    @Test
    void getTotalDiscountByMonth() {
        Month month = dateToSearch.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth();
        Double sum = movements.stream()
                .filter(o-> o.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth().equals(month))
                .mapToDouble(Movement::getDiscount)
                .sum();
        when(movementRepository.getTotalDiscountByMonth(month.ordinal())).thenReturn(sum);
        assertNotEquals(0, movementService.getTotalDiscountByMonth(month.ordinal()));
        verify(movementRepository, times(1)).getTotalDiscountByMonth(month.ordinal());
    }

    @Test
    void getTotalDiscountByYear() {
        int year = dateToSearch.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        Double sum = movements.stream()
                .filter(o-> o.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()==year)
                .mapToDouble(Movement::getDiscount)
                .sum();
        when(movementRepository.getTotalDiscountByYear(year)).thenReturn(sum);
        assertNotEquals(0, movementService.getTotalDiscountByYear(year));
        verify(movementRepository, times(1)).getTotalDiscountByYear(year);
    }
}