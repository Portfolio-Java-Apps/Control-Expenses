package dev.gclopes.ControlExpenses.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "MiscExpense")
@Table(name = "MiscExpense")
@NaturalIdCache
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
@NoArgsConstructor
public class MiscExpense extends BaseEntity {

    @NaturalId
    @Column(name = "Desc")
    private String desc;

    @OneToMany(
            mappedBy = "miscExpense",
            cascade = CascadeType.ALL
    )
    private List<MovMiscExpense> movMiscExpenses = new ArrayList<>();


    public MiscExpense(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiscExpense miscExpense = (MiscExpense) o;
        return Objects.equals(desc, miscExpense.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc);
    }


    public List<MovMiscExpense> getMovMiscExpenses() {
        return movMiscExpenses;
    }

    public void setMovMiscExpenses(List<MovMiscExpense> movMiscExpenses) {
        this.movMiscExpenses = movMiscExpenses;
    }
}
