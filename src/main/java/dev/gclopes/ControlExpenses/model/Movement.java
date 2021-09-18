package dev.gclopes.ControlExpenses.model;

import dev.gclopes.ControlExpenses.enumType.Type;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "Post")
@Table(name = "post")
@Getter
@Setter
@Builder
public class Movement extends BaseEntity{
    @Column(name="Type")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "source")
    private Source source;

    @Column(name="Date")
    private Date date;

    @Column(name="TotalAmount")
    private Double totalAmount;
    @Column(name="Discount")
    private Double discount;



    @ManyToOne
    @JoinColumn(name = "TypeOfPayment")
    private TypeOfPayment typeOfPayment;

    @OneToMany(
            mappedBy = "movement",
            cascade = CascadeType.ALL
    )
    private List<MovMiscExpense> movMiscExpenses;

    @OneToMany(
            mappedBy = "movement",
            cascade = CascadeType.ALL
    )
    private List<MovPersonalGoods> movPersonalGoods;

}
