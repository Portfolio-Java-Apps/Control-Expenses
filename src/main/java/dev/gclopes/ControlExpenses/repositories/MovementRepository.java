package dev.gclopes.ControlExpenses.repositories;

import dev.gclopes.ControlExpenses.enumType.Type;
import dev.gclopes.ControlExpenses.model.Movement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface MovementRepository extends CrudRepository<Movement, Long> {
    Set<Movement> findByType(Type Type);
    Set<Movement> findByDate(Date date);
    @Query(value="SELECT SUM(total_amount) FROM movement WHERE date = :dayToCheck")
    Double getTotalAmountByDate(@Param("dayToCheck") Date date);
    @Query(value="SELECT SUM(m.total_amount) FROM movement m WHERE month(m.date) = :monthToCheck")
    Double getTotalAmountByMonth(@Param("monthToCheck") Integer month);
    @Query(value="SELECT SUM(m.total_amount) FROM movement m WHERE month(m.date) = :yearToCheck")
    Double getTotalAmountByYear(@Param("yearToCheck") Integer year);
    @Query(value="SELECT SUM(discount) FROM movement WHERE date = :dayToCheck")
    Double getTotalDiscountByDate(@Param("dayToCheck") Date date);
    @Query(value="SELECT SUM(m.discount) FROM movement m WHERE month(m.date) = :monthToCheck")
    Double getTotalDiscountByMonth(@Param("monthToCheck") Integer month);
    @Query(value="SELECT SUM(m.discount) FROM movement m WHERE month(m.date) = :yearToCheck")
    Double getTotalDiscountByYear(@Param("yearToCheck") Integer year);

}
