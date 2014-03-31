package algorithm;

import java.util.ArrayList;

import plot.Pair;
import plot.SimulationParameter;

/**
 * @author: Levi
 * @version: 2/16/14
 */
public class LineBuilder {

	public static ArrayList<Pair> getLine(
			SimulationParameter independentParameter, double min, double max,
			ParameterPackage parameterPackage, double spacingBetweenDataPoints) {
		ArrayList<Pair> lineArray = new ArrayList<Pair>();
		for (double i = min; i < max; i += spacingBetweenDataPoints) {
			switch (independentParameter) {
			case LeafWidth:
				parameterPackage.setLeafWidth(i);
				break;
			case LeafArea:
				parameterPackage.setLeafArea(i);
				break;
			case StomaDensity:
				parameterPackage.setStomaDensity(i);
				break;
			case StomaRadius:
				parameterPackage.setStomaRadius(i);
				break;
			case StomaDepth:
				parameterPackage.setStomaDepth(i);
				break;
			case Temperature:
				parameterPackage.setTemperature(i);
				break;
			case RelativeHumidityOfAir:
				parameterPackage.setHumidity(i);
				break;
			case WindSpeed:
				parameterPackage.setWindspeed(i);
				break;
			}

			lineArray.add(new Pair(i, WaterLossAlgorithm
					.getWaterLoss(parameterPackage)));
		}
		return lineArray;
	}
}
