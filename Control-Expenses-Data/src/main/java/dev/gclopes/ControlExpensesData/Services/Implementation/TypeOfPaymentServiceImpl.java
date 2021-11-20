package dev.gclopes.ControlExpensesData.Services.Implementation;

import dev.gclopes.ControlExpensesData.Services.TypeOfPaymentService;
import dev.gclopes.ControlExpensesData.model.TypeOfPayment;
import dev.gclopes.ControlExpensesData.repositories.TypeOfPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("TypeOfPaymentService")
public class TypeOfPaymentServiceImpl implements TypeOfPaymentService {

    private final TypeOfPaymentRepository typeOfPaymentRepository;

    public TypeOfPaymentServiceImpl(TypeOfPaymentRepository typeOfPaymentRepository) {
        this.typeOfPaymentRepository = typeOfPaymentRepository;
    }


    @Override
    public Set<TypeOfPayment> findAll() {
        Set<TypeOfPayment> typeOfPayments = new HashSet<>();
        typeOfPaymentRepository.findAll().forEach(typeOfPayments::add);
        return typeOfPayments;
    }

    @Override
    public TypeOfPayment findById(Long aLong) {
        return typeOfPaymentRepository.findById(aLong).orElse(null);
    }

    @Override
    public TypeOfPayment save(TypeOfPayment object) {
        return typeOfPaymentRepository.save(object);
    }

    @Override
    public void delete(TypeOfPayment object) {
        typeOfPaymentRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        typeOfPaymentRepository.deleteById(aLong);
    }

    @Override
    public TypeOfPayment findByName(String name) {
        return typeOfPaymentRepository.findByName(name).orElse(null);
    }
}
