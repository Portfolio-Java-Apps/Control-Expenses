package dev.gclopes.ControlExpenses;

import dev.gclopes.ControlExpenses.Services.MiscExpenseService;
import dev.gclopes.ControlExpenses.Services.SourceService;
import dev.gclopes.ControlExpenses.Services.TypeOfPaymentService;
import dev.gclopes.ControlExpenses.repositories.MiscExpenseRepository;
import dev.gclopes.ControlExpenses.repositories.SourceRepository;
import dev.gclopes.ControlExpenses.repositories.TypeOfPaymentRepository;
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
	private SourceService sourceService;

	@Autowired
	private MiscExpenseService miscExpenseService;

	@Autowired
	private TypeOfPaymentService typeOfPaymentService;

	@Test
	void contextLoads() {
		assertNotNull(sourceService);
		assertNotNull(typeOfPaymentService);
		assertNotNull(miscExpenseService);
	}

}
