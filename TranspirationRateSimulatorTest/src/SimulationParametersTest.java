import com.transpirationRateSimulator.model.SimulationParameter;

import TestHelpers.TestCaseHelper;


public class SimulationParametersTest extends TestCaseHelper{

	public void testParametersHaveCorrectViewableNames() throws Exception {
		SimulationParameter[] values = SimulationParameter.values();
		assertEquals(8,values.length);
		assertEquals("Leaf Width (cm)", values[0].toString());
		assertEquals("Leaf Area (cm�)", values[1].toString());
		assertEquals("Stoma Density (#/million�)", values[2].toString());
		assertEquals("Stoma Radius (�m)", values[3].toString());
		assertEquals("Stoma Depth (�m)", values[4].toString());
		assertEquals("Temperature (c)", values[5].toString());
		assertEquals("Relative Humidity (%)", values[6].toString());
		assertEquals("Wind Speed (mph)", values[7].toString());
	}
	
}
