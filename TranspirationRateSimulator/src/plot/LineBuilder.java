package plot;

import java.util.ArrayList;

/**
 * @author: Levi
 * @version: 2/16/14
 */
public class LineBuilder {
    private static final int PIXEL_RANGE = 200;
    private static ArrayList<Pair> lineArray = new ArrayList<Pair>();

    public static ArrayList<Pair> getLine (SimulationParameters indVariable, int min, int max, double leafWidth, double leafArea, double stomaDensity,
                        double stomaRadius, double stomaDepth, double temperature, double humidity, double windspeed){
        double increment = ((double) (max - min)) / PIXEL_RANGE;

        switch (indVariable){
            case LeafWidth:
                for (double i = min; i < max; i += increment) {
                    lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(i, leafArea, stomaDensity, stomaRadius, stomaDepth,
                            temperature, humidity, windspeed)));
                }
                break;
            case LeafArea:
                for (double i = min; i < max; i += increment) {
                    lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, i, stomaDensity, stomaRadius, stomaDepth,
                            temperature, humidity, windspeed)));
                }
                break;
            case StomaDensity:
                for (double i = min; i < max; i += increment) {
                    lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, i, stomaRadius, stomaDepth,
                            temperature, humidity, windspeed)));
                }
                break;
            case StomaRadius:
                for (double i = min; i < max; i += increment) {
                    lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, i, stomaDepth,
                            temperature, humidity, windspeed)));
                }
                break;
            case StomaDepth:
                for (double i = min; i < max; i += increment) {
                    lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, stomaRadius, i,
                            temperature, humidity, windspeed)));
                }
                break;
            case Temperature:
                for (double i = min; i < max; i += increment) {
                    lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, stomaRadius, stomaDepth,
                            i, humidity, windspeed)));
                }
                break;
            case RelativeHumidityOfAir:
                for (double i = min; i < max; i += increment) {
                    lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, stomaRadius, stomaDepth,
                            temperature, i, windspeed)));
                }
                break;
            case WindSpeed:
                for (double i = min; i < max; i += increment) {
                    lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, stomaRadius, stomaDepth,
                            temperature, humidity, i)));
                }
                break;
        }

        return lineArray;
    }
}
