package Group1.Mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(MockApplication.class)
class MockApplicationTests {

	@Autowired
	private MockApplication mockApplication;

	@MockBean
	private ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor;

	@BeforeEach
	void setUp() {
		assertNotNull(mockApplication);
	}

	@Test
	void contextLoads() {
		// This test ensures that the application context loads successfully
		assertTrue(true);
	}

	@Test
	void scheduledAnnotationBeanPostProcessorIsAutowired() {
		// This test verifies that the ScheduledAnnotationBeanPostProcessor is autowired
		assertNotNull(scheduledAnnotationBeanPostProcessor);
	}


	@Test
	void springBootApplicationAnnotationPresent() {
		// This test checks if the @SpringBootApplication annotation is present on the MockApplication class
		assertTrue(mockApplication.getClass().isAnnotationPresent(SpringBootApplication.class));
	}
	@Test
	public void testSpringBootApplicationAnnotation() {
		assertTrue(mockApplication.getClass().isAnnotationPresent(SpringBootApplication.class));
	}

//	@Test
//	public void testEnableSchedulingAnnotation() {
//		assertTrue(mockApplication.getClass().isAnnotationPresent(EnableScheduling.class));
//	}

	@Test
	public void testScheduledAnnotationBeanPostProcessorIsMocked() {
		assertNotNull(scheduledAnnotationBeanPostProcessor);
	}

	@Test
	public void testMainMethod() {
		// This test is not ideal as it's testing the Spring Boot framework's behavior
		// However, it's included for completeness
		try {
			MockApplication.main(new String[]{});
		} catch (Exception e) {
			assertTrue(false, "Exception occurred while running main method: " + e.getMessage());
		}
	}
}