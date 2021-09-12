package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.model.Source;
import dev.gclopes.ControlExpenses.repositories.SourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SourceServiceImplTest {

    @Mock()
    private SourceRepository sourceRepository;
    private SourceServiceImpl sourceService;

    private Set<Source> sources;
    private String[] ArrStrDesc = { "Continente", "Jumbo", "Leroy Merlin", "Lusiadas/Nuno", "Praça/Alfragide",
                                    "Praça/Buraca", "Celeiro", "Conta/Manutenção"};

    @BeforeEach
    void setUp() {
        sourceService = new SourceServiceImpl(sourceRepository);
        sources = new HashSet<>();

        for (String desc: ArrStrDesc) {

            sources.add(new Source(desc));
        }
    }

    @Test
    void findAll() {
        Mockito.when(sourceRepository.findAll()).thenReturn(sources);

        assertNotNull(sourceService.findAll());
        Mockito.verify(sourceRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Long id = 1L;
        Source source = new Source("TEST");
        Mockito.when(sourceRepository.findById(id)).thenReturn(Optional.of(source));
        assertNotNull(sourceService.findById(id));
        Mockito.when(sourceRepository.findById(id)).thenReturn(Optional.empty());
        assertNull(sourceService.findById(id));
        Mockito.verify(sourceRepository, times(2)).findById(id);
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

    @Test
    void findByDesc() {
    }
}