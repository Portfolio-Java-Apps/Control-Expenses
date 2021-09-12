package dev.gclopes.ControlExpenses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class TypeOfPayment extends BaseEntity{

    private String desc;

    @OneToMany(mappedBy = "typeOfPayment", cascade = CascadeType.ALL)
    @ToString.Exclude
    Set<Movement> movements = new HashSet<>();


}
