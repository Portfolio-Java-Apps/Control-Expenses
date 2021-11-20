package dev.gclopes.ControlExpensesData;

import dev.gclopes.ControlExpensesData.Services.*;
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
	private SourceService sourceService;

	@Autowired
	private MiscExpenseService miscExpenseService;

	@Autowired
	private MovMiscExpenseService movMiscExpenseService;

	@Autowired
	private TypeOfPaymentService typeOfPaymentService;

	@Autowired
	private PersonalGoodsService personalGoodsService;

	@Autowired
	private MovementService movementService;

	@Autowired
	private MovPersonalGoodsService movPersonalGoodsService;

	@Test
	void contextLoads() {
		assertNotNull(sourceService);
		assertNotNull(typeOfPaymentService);
		assertNotNull(miscExpenseService);
		assertNotNull(movMiscExpenseService);
		assertNotNull(personalGoodsService);
		assertNotNull(movPersonalGoodsService);
		assertNotNull(movementService);
	}

}
