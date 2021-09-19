package dev.gclopes.ControlExpenses.model;

import dev.gclopes.ControlExpenses.enumType.Type;
import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity(name = "Post")
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movement extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }
    public void SetId(Long id){
        this.setId(id);
    }
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movement{");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        sb.append("type=").append(type);
        if(source!=null){
            sb.append(", sourceId=").append(source.getId());
            sb.append(", Source Desc=").append(source.getDesc());
        }
        sb.append(", date=").append(formatter.format(date));
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", discount=").append(discount);
        if(typeOfPayment!=null) {
            sb.append(", Type of Payment Id=").append(typeOfPayment.getId());
            sb.append(", Type of Payment=").append(typeOfPayment.getDesc());
        }
       /*
        sb.append(", typeOfPayment=").append(typeOfPayment);
        sb.append(", movMiscExpenses=").append(movMiscExpenses);
        sb.append(", movPersonalGoods=").append(movPersonalGoods);*/
        sb.append('}');
        return sb.toString();
    }

    @ManyToOne
    @JoinColumn(name = "TypeOfPayment")
    private TypeOfPayment typeOfPayment;

    @OneToMany(
            mappedBy = "movement",
            cascade = CascadeType.ALL
    )
    private Set<MovMiscExpense> movMiscExpenses;

    @OneToMany(
            mappedBy = "movement",
            cascade = CascadeType.ALL
    )
    private List<MovPersonalGoods> movPersonalGoods;

}
