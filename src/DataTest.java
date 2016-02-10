import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataTest {
	Data d = new Data();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		d.getUnitId();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetData() {
		assertEquals(23, d.unitIds.size());
	}


	
}
