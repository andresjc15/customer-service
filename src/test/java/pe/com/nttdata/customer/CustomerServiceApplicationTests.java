package pe.com.nttdata.customer;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import pe.com.nttdata.customer.model.document.EnterpriseCustomer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplicationTests.class);

	private EnterpriseCustomer enterpriseCustomer;
	private TestInfo testInfo;
	private TestReporter testReporter;
	@BeforeEach
	void initMethodTest(TestInfo testInfo, TestReporter testReporter) {
		this.enterpriseCustomer = new EnterpriseCustomer(001L, "Av. La marina 1144", "Bussines N", 1641648549917L);
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		log.info("Iniciando el metodo test");
		testReporter.publishEntry(" ejecutando: " + testInfo.getDisplayName() + " "
				+ testInfo.getTestMethod().orElse(null).getName()
				+ " con las etiquetas " + testInfo.getTags());
	}

	@Test
	@DisplayName("Error en caso de prueba")
	void testRuc() {
		assertNotNull(enterpriseCustomer.getRuc());
		assertEquals(1641648549917L, enterpriseCustomer.getRuc().longValue());
		assertFalse(enterpriseCustomer.getRuc().compareTo(0L) < 0);
		assertTrue(enterpriseCustomer.getRuc().compareTo(0L) > 0);
	}

}
