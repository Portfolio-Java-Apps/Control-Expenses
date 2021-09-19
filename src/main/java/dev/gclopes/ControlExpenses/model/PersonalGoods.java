package dev.gclopes.ControlExpenses.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "PersonalGoods")
@Table(name = "PersonalGoods")
@NaturalIdCache
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
@NoArgsConstructor
public class PersonalGoods extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }
    public void SetId(Long id){
        this.setId(id);
    }
    private String desc;

    @OneToMany(
            mappedBy = "personalGoods"
    )
    private Set<MovPersonalGoods> movPersonalGoods;

    public PersonalGoods(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalGoods personalGoods = (PersonalGoods) o;
        return Objects.equals(desc, personalGoods.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc);
    }

}
