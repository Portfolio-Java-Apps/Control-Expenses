package dev.gclopes.ControlExpenses;

import dev.gclopes.ControlExpenses.Services.SourceService;
import dev.gclopes.ControlExpenses.repositories.SourceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ControlExpensesApplicationTests {

	@Mock()
	private SourceRepository sourceRepository;

	@Autowired
	private SourceService sourceService;

	@Test
	void contextLoads() {
		assertNotNull(sourceService);
	}

}
