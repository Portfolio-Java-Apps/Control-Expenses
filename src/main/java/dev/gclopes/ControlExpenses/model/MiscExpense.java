package dev.gclopes.ControlExpenses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "MiscExpense")
@Table(name = "MiscExpense")
@NoArgsConstructor
@Getter
@Setter
public class MiscExpense extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "Desc")
    private String desc;

    @OneToMany(
            mappedBy = "miscExpense")
    private Set<MovMiscExpense> movMiscExpenses = new HashSet<>();


    public MiscExpense(long id, String desc) {
        this.setId(id);
        this.desc = desc;
    }





}
