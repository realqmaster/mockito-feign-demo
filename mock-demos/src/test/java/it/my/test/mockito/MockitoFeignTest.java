package it.my.test.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import it.my.test.MockDemosApplication;
import it.my.test.MockableFeignClient;
import it.my.test.MockablePojo;
import it.my.test.TestableService;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = MockDemosApplication.class)
@ActiveProfiles("test")
public class MockitoFeignTest {
	
	@Autowired
	private TestableService srv;
	
	@MockBean
	private MockableFeignClient cli;

	private static PodamFactoryImpl factory;
	
	@BeforeClass
	public static void setupFactory() {
		factory = new PodamFactoryImpl();
	}
	
	
	
	@Test
	public void testThemock() {
		Mockito.when(cli.getFoo()).thenReturn("MOCK");
		
		String result  = srv.getFoo();
		assertEquals("WTF", "MOCK", result);
	}
	
	@Test
	public void testPojo() {
		MockablePojo mock = factory.manufacturePojo(MockablePojo.class);
		mock.setDesc("MOCK");

		Mockito.when(cli.getPojo()).thenReturn(mock);		
		MockablePojo result = srv.getPojo();
		
		assertEquals("WTF", "MOCK", result.getDesc());
		assertNotNull(result.getBirthday());
		assertNotNull(result.getId());
		
		
	}

}
