package dev.gclopes.ControlExpenses.Services.Implementation;

import dev.gclopes.ControlExpenses.model.PersonalGoods;
import dev.gclopes.ControlExpenses.repositories.PersonalGoodsRepository;
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
class PersonalGoodsServiceImplTest {

    @Mock()
    private PersonalGoodsRepository personalGoodsRepository;
    private PersonalGoodsServiceImpl personalGoodsService;

    private PersonalGoods personalGood;
    private Set<PersonalGoods> personalGoods;
    private long id;
    private Random rand;
    private final String[] ArrStrDesc = {"Mercearia", "Bebidas", "DP Higiene", "Lactic/Cong.", "Talho", "Peixaria",
                                         "Charcutaria", "Frutas e Legumes", "Bio e Saudável", "Padaria", "Take-Away",
                                         "Casa", "Outros", "Descontos"};
    @BeforeEach
    void setUp() {
        personalGoodsService = new PersonalGoodsServiceImpl(personalGoodsRepository);
        personalGoods = new HashSet<>();
        long personalGoodsId=0;
        for(String s : ArrStrDesc)
        {
            PersonalGoods PersonalGoods = new PersonalGoods(++personalGoodsId, s);
            personalGoods.add(PersonalGoods);
        }
        rand=new Random();
        id = (long) (random() * ((long) personalGoods.size() -1) + 1);
        personalGood = personalGoods.stream().filter(x-> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }

    @Test
    void findByName() {
        int id = rand.nextInt(personalGoods.size());
        String strName =  ArrStrDesc[id];
        PersonalGoods myPersonalGoods = personalGoods.stream().filter(x-> Objects.equals(x.getName(),strName)).findFirst().orElse(null);
        when(personalGoodsRepository.findByName(strName)).thenReturn(Optional.ofNullable(myPersonalGoods));
        personalGoodsService.findByName(strName);
        verify(personalGoodsRepository, times(1)).findByName(strName);
    }

    @Test
    void findAll() {
        when(personalGoodsRepository.findAll()).thenReturn(personalGoods);
        assertNotNull(personalGoodsService.findAll());
        verify(personalGoodsRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(personalGoodsRepository.findById(id)).thenReturn(personalGood !=null ? Optional.of(personalGood) : Optional.empty());
        assertNotNull(personalGoodsService.findById(id));
        when(personalGoodsRepository.findById(id)).thenReturn(Optional.empty());
        assertNull(personalGoodsService.findById(id));
        verify(personalGoodsRepository, times(2)).findById(id);

    }

    @Test
    void save() {
        assertNotNull(personalGood);
        when(personalGoodsRepository.save(personalGood)).thenReturn(personalGood);
        personalGoodsService.save(personalGood);
        verify(personalGoodsRepository, times(1)).save(personalGood);
    }

    @Test
    void delete() {
        personalGoodsService.delete(personalGood);
        verify(personalGoodsRepository, times(1)).delete(personalGood);
    }

    @Test
    void deleteById() {
        personalGoodsService.deleteById(id);
        verify(personalGoodsRepository, times(1)).deleteById(id);
    }
}