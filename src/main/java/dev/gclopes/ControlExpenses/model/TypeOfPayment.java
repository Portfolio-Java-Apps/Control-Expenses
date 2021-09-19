package dev.gclopes.ControlExpenses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="TypeOfPayment")
@Table(name="TypeOfPayment")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class TypeOfPayment extends BaseEntity{
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

    @OneToMany(mappedBy = "typeOfPayment", cascade = CascadeType.ALL)
    @ToString.Exclude
    Set<Movement> movements = new HashSet<>();


}
