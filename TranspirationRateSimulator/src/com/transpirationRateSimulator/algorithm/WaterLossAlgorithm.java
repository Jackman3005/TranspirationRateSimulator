package com.transpirationRateSimulator.algorithm;

import com.transpirationRateSimulator.model.ParameterPackage;
import com.transpirationRateSimulator.model.SimulationParameter;

public class WaterLossAlgorithm {

	// Constants
	private static final double DIAS = 6 * Math.pow(10, -5);
	private static final double GC = 0.001;
	private static final double HIAS = 1.0;
	private static final double MP = 0.4470;

	public static double getWaterLoss(ParameterPackage parameterPackage) {

		double leafWidth = parameterPackage
				.getParameterValue(SimulationParameter.LeafWidth);
		double leafArea = parameterPackage
				.getParameterValue(SimulationParameter.LeafArea);
		double stomaDensity = parameterPackage
				.getParameterValue(SimulationParameter.StomaDensity);
		double stomaRadius = parameterPackage
				.getParameterValue(SimulationParameter.StomaRadius);
		double stomaDepth = parameterPackage
				.getParameterValue(SimulationParameter.StomaDepth);
		double temperature = parameterPackage
				.getParameterValue(SimulationParameter.Temperature);
		double humidity = parameterPackage
				.getParameterValue(SimulationParameter.RelativeHumidityOfAir);
		double windspeed = parameterPackage
				.getParameterValue(SimulationParameter.WindSpeed);

		double dwv, ast, gst, gias, gleaf, gbl, gttal, cias, cair, dc, jwv, wl, cwv, waterloss;

		// These only rely on user input for calculations
		dwv = (2.13 * Math.pow(10, -5))
				* (Math.pow(((temperature + 273) / 273), 1.8));
		ast = Math.PI * (Math.pow((stomaRadius * Math.pow(10, -6)), 2));
		cwv = 2.848 + (0.1082 * temperature)
				+ ((3.283 * Math.pow(10, -4)) * Math.pow(temperature, 2))
				+ ((2.06 * Math.pow(10, -5)) * Math.pow(temperature, 3));
		gbl = dwv
				/ (4 * Math.pow((leafWidth / 100)
						/ ((windspeed + 0.00001) * MP), 0.5));

		// These rely on the above
		gst = (dwv * (stomaDensity * Math.pow(10, 6)) * ast)
				/ ((stomaDepth * Math.pow(10, -6)) + (stomaRadius * Math.pow(
						10, -6)));
		cias = cwv * HIAS;
		cair = (humidity / 100) * cwv;
		gias = dwv / DIAS;

		// These rely on each other, in order
		gleaf = ((gias * gst) / (gias + gst)) + GC;
		gttal = (gleaf * gbl) / (gleaf + gbl);
		dc = cias - cair;
		jwv = gttal * dc * 18;
		wl = (jwv * (leafArea * Math.pow(10, -4)) * 3.6 * Math.pow(10, 3));

		// Waterloss for 100-leaf plant
		waterloss = 100 * wl;

		return waterloss;

	}

}
