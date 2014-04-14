package com.transpirationRateSimulator.algorithm;

import java.util.ArrayList;

import com.transpirationRateSimulator.model.ParameterPackage;
import com.transpirationRateSimulator.model.SimulationParameter;
import com.transpirationRateSimulator.plot.Pair;

public class LineBuilder {

	public static ArrayList<Pair> getLine(
			SimulationParameter independentParameter, double min, double max,
			ParameterPackage parameterPackage, double spacingBetweenDataPoints) {
		ArrayList<Pair> lineArray = new ArrayList<Pair>();
		ParameterPackage parameterPackageCopy = new ParameterPackage(
				parameterPackage);
		for (double i = min; i < max; i += spacingBetweenDataPoints) {
			parameterPackageCopy.setParameterValue(independentParameter, i);
			lineArray.add(new Pair(i, WaterLossAlgorithm
					.getWaterLoss(parameterPackageCopy)));
		}
		return lineArray;
	}
}
