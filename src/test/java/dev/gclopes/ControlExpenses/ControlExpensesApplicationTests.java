package dev.gclopes.ControlExpenses;

import dev.gclopes.ControlExpenses.Services.*;
import dev.gclopes.ControlExpenses.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Configuration
@ContextConfiguration
@SpringBootTest(classes = ControlExpensesApplication.class)
class ControlExpensesApplicationTests {

	@Autowired
	private SourceRepository sourceRepository;

	@Autowired
	private MiscExpenseRepository miscExpenseRepository;

	@Autowired
	private TypeOfPaymentRepository typeOfPaymentRepository;

	@Autowired
	private PersonalGoodsRepository personalGoodsRepository;

	@Autowired
	private MovementRepository movementRepository;

	@Autowired
	private SourceService sourceService;

	@Autowired
	private MiscExpenseService miscExpenseService;

	@Autowired
	private TypeOfPaymentService typeOfPaymentService;

	@Autowired
	private PersonalGoodsService personalGoodsService;

	@Autowired
	private MovementService movementService;

	@Test
	void contextLoads() {
		assertNotNull(sourceService);
		assertNotNull(typeOfPaymentService);
		assertNotNull(miscExpenseService);
		assertNotNull(personalGoodsService);
		assertNotNull(movementService);
	}

}
