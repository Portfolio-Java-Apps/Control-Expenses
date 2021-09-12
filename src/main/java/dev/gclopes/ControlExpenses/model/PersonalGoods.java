package dev.gclopes.ControlExpenses.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "PersonalGoods")
@Table(name = "PersonalGoods")
@NaturalIdCache
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
@NoArgsConstructor
public class PersonalGoods extends BaseEntity{
    @NaturalId
    private String desc;

    @OneToMany(
            mappedBy = "personalGoods",
            cascade = CascadeType.ALL
    )
    private List<MovPersonalGoods> movPersonalGoods = new ArrayList<>();

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
