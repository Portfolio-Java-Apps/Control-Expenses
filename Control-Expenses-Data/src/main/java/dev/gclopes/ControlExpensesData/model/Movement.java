package dev.gclopes.ControlExpensesData.model;

import dev.gclopes.ControlExpensesData.enumType.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


@Entity(name = "movement")
@Table(name = "movement")
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movement extends BaseEntity{
    public Movement(Long id)
    {
        super.setId(id);
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Override


    public Long getId() {
        return super.getId();
    }
    public void setId(Long id){
        super.setId(id);
    }
    @Column(name="Type")
    private Type type;

    public Movement() {
        this.totalAmount=0.00;
        this.discount=0.00;
    }

    private Source source;

    @Column(name="Date")
    private Date date=new Date();

    @Column(name="TotalAmount")
    private Double totalAmount;
    @Column(name="Discount")
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "source")
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movement{");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        sb.append("type=").append(type);
        if(source!=null){
            sb.append(", sourceId=").append(source.getId());
            sb.append(", Source Desc=").append(source.getName());
        }
        sb.append(", date=").append(formatter.format(date));

        sb.append(", totalAmount=").append(String.format("%.02f",totalAmount));
        sb.append(", discount=").append(String.format("%.02f",discount));
        if(typeOfPayment!=null) {
            sb.append(", Type of Payment Id=").append(typeOfPayment.getId());
            sb.append(", Type of Payment=").append(typeOfPayment.getName());
        }
       /*
        sb.append(", typeOfPayment=").append(typeOfPayment);
        sb.append(", movMiscExpenses=").append(movMiscExpenses);
        sb.append(", movPersonalGoods=").append(movPersonalGoods);
        */
        sb.append('}');
        return sb.toString();
    }

    private TypeOfPayment typeOfPayment;

    @ManyToOne
    @JoinColumn(name = "TypeOfPayment")
    public TypeOfPayment getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(TypeOfPayment typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    @OneToMany(
            mappedBy = "movement",
            fetch = FetchType.LAZY
    )
    @Column(name="mov_misc_expenses_id")
    public Collection<MovMiscExpense> getMovMiscExpenses() {
        return movMiscExpenses;
    }

    public void setMovMiscExpenses(Collection<MovMiscExpense> movMiscExpenses) {
        this.movMiscExpenses = movMiscExpenses;
    }

    @OneToMany(
            mappedBy = "movement",
            fetch = FetchType.LAZY
    )
    @Column(name="mov_personal_goods_id")
    public Collection<MovPersonalGoods> getMovPersonalGoods() {
        return movPersonalGoods;
    }

    public void setMovPersonalGoods(Collection<MovPersonalGoods> movPersonalGoods) {
        this.movPersonalGoods = movPersonalGoods;
    }

    private Collection<MovMiscExpense> movMiscExpenses;

    @OneToMany(
            mappedBy = "movement",
            cascade = CascadeType.ALL
    )
    private Collection<MovPersonalGoods> movPersonalGoods;

}
