import com.transpirationRateSimulator.model.SimulationParameter;

import TestHelpers.TestCaseHelper;


public class SimulationParametersTest extends TestCaseHelper{

	public void testParametersHaveCorrectViewableNames() throws Exception {
		SimulationParameter[] values = SimulationParameter.values();
		assertEquals(8,values.length);
		assertEquals("Leaf Width (cm)", values[0].toString());
		assertEquals("Leaf Area (cm²)", values[1].toString());
		assertEquals("Stoma Density (#/million²)", values[2].toString());
		assertEquals("Stoma Radius (µm)", values[3].toString());
		assertEquals("Stoma Depth (µm)", values[4].toString());
		assertEquals("Temperature (c)", values[5].toString());
		assertEquals("Relative Humidity (%)", values[6].toString());
		assertEquals("Wind Speed (mph)", values[7].toString());
	}
	
}
