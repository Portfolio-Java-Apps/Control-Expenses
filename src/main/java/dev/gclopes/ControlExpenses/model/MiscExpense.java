package dev.gclopes.ControlExpenses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "misc_expense")
@Table(name = "misc_expense")
@NoArgsConstructor
@Getter
@Setter
public class MiscExpense extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "Name")
    private String name;


    private List<MovMiscExpense> movMiscExpenses = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "miscExpense", fetch = FetchType.LAZY)
    @Column(name="mov_misc_expense_id")
    public List<MovMiscExpense> getMovMiscExpenses() {
        return movMiscExpenses;
    }

    public void setMovMiscExpenses(List<MovMiscExpense> movMiscExpenses) {
        this.movMiscExpenses = movMiscExpenses;
    }

    public MiscExpense(long id, String name) {
        this.setId(id);
        this.name = name;
    }





}
