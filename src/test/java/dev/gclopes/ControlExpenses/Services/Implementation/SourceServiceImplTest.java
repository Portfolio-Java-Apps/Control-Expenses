package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.enumType.Type;
import dev.gclopes.ControlExpenses.model.Movement;
import dev.gclopes.ControlExpenses.model.Source;
import dev.gclopes.ControlExpenses.repositories.SourceRepository;
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
class SourceServiceImplTest {

    @Mock()
    private SourceRepository sourceRepository;
    private SourceServiceImpl sourceService;

    private Set<Source> sources;
    private final String[] ArrStrDesc = { "Continente", "Jumbo", "Leroy Merlin", "Lusiadas/Nuno", "Praça/Alfragide",
                                    "Praça/Buraca", "Celeiro", "Conta/Manutenção"};

    private long id;
    private Source source;
    private Random rand;
    @BeforeEach
    void setUp() {
        sourceService = new SourceServiceImpl(sourceRepository);
        sources = new HashSet<>();

        long sourceId=0;
        for(String s : ArrStrDesc)
        {
           Source source = new Source(++sourceId, s);
           sources.add(source);
        }

        rand=new Random();
        id = (long) (random() * ((long) sources.size() -1) + 1);
        source = sources.stream().filter(x-> Objects.equals(x.id, id)).findFirst().orElse(null);

    }

    @Test
    void findAll() {
        when(sourceRepository.findAll()).thenReturn(sources);
        assertNotNull(sourceService.findAll());
        verify(sourceRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(sourceRepository.findById(id)).thenReturn(source!=null ? Optional.of(source) : Optional.empty());
        assertNotNull(sourceService.findById(id));
        when(sourceRepository.findById(id)).thenReturn(Optional.empty());
        assertNull(sourceService.findById(id));
        verify(sourceRepository, times(2)).findById(id);
    }

    @Test
    void save() {
        assertNotNull(source);

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
                    .source(source)
                    .discount(TotalDiscount)
                    .totalAmount(TotalAmount)
                    .build();
            movement.setId((long) i+1);
            System.out.println(movement);
            movements.add(movement);
        }
        source.setMovements(movements);
        when(sourceRepository.save(source)).thenReturn(source);
        sourceService.save(source);
        verify(sourceRepository, times(1)).save(source);
    }

    @Test
    void delete() {
        sourceService.delete(source);
        verify(sourceRepository, times(1)).delete(source);
    }

    @Test
    void deleteById() {
        sourceService.deleteById(id);
        verify(sourceRepository, times(1)).deleteById(id);
    }

    @Test
    void findByName() {
        int id = rand.nextInt(ArrStrDesc.length)-1;
        String strName =  ArrStrDesc[id];
        Source mySource = sources.stream().filter(x-> Objects.equals(x.getName(),strName)).findFirst().orElse(null);
        when(sourceRepository.findByName(strName)).thenReturn(Optional.ofNullable(mySource));
        sourceService.findByName(strName);
        verify(sourceRepository, times(1)).findByName(strName);
    }
}