package dev.gclopes.ControlExpenses.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

    @Column(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(
            mappedBy = "personalGoods",
            fetch=FetchType.LAZY
    )
    @Column(name="mov_personal_goods_id")
    private Collection<MovPersonalGoods> movPersonalGoods = new ArrayList<>();

    public PersonalGoods(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalGoods personalGoods = (PersonalGoods) o;
        return Objects.equals(name, personalGoods.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
