package plot;

import java.util.ArrayList;

/**
 * @author: Levi
 * @version: 2/16/14
 */
public class LineBuilder {
    private static final int PIXEL_RANGE = 200;
    private double increment;
    private ArrayList<Pair> lineArray = new ArrayList<Pair>();
    private int min;
    private int max;
    private final double leafWidth;
    private final double leafArea;
    private final double stomaDensity;
    private final double stomaRadius;
    private final double stomaDepth;
    private final double temperature;
    private final double humidity;
    private final double windspeed;

    public LineBuilder (SimulationParameters indVariable, int min, int max, double leafWidth, double leafArea, double stomaDensity,
                        double stomaRadius, double stomaDepth, double temperature, double humidity, double windspeed){
        this.min = min;
        this.max = max;
        this.leafWidth = leafWidth;
        this.leafArea = leafArea;
        this.stomaDensity = stomaDensity;
        this.stomaRadius = stomaRadius;
        this.stomaDepth = stomaDepth;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.increment = ((double) (max - min)) / PIXEL_RANGE;

        populateArray(indVariable);
    }

    private void populateArray(SimulationParameters indVariable){
        switch (indVariable){
            case LeafWidth:
                for (double i = min; i < max; i += increment) {
                    this.lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(i, leafArea, stomaDensity, stomaRadius, stomaDepth,
                            temperature, humidity, windspeed)));
                }
                break;
            case LeafArea:
                for (double i = min; i < max; i += increment) {
                    this.lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, i, stomaDensity, stomaRadius, stomaDepth,
                            temperature, humidity, windspeed)));
                }
                break;
            case StomaDensity:
                for (double i = min; i < max; i += increment) {
                    this.lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, i, stomaRadius, stomaDepth,
                            temperature, humidity, windspeed)));
                }
                break;
            case StomaRadius:
                for (double i = min; i < max; i += increment) {
                    this.lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, i, stomaDepth,
                            temperature, humidity, windspeed)));
                }
                break;
            case StomaDepth:
                for (double i = min; i < max; i += increment) {
                    this.lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, stomaRadius, i,
                            temperature, humidity, windspeed)));
                }
                break;
            case Temperature:
                for (double i = min; i < max; i += increment) {
                    this.lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, stomaRadius, stomaDepth,
                            i, humidity, windspeed)));
                }
                break;
            case RelativeHumidityOfAir:
                for (double i = min; i < max; i += increment) {
                    this.lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, stomaRadius, stomaDepth,
                            temperature, i, windspeed)));
                }
                break;
            case WindSpeed:
                for (double i = min; i < max; i += increment) {
                    this.lineArray.add(new Pair(i, WaterLossAlgorithm.getWaterLoss(leafWidth, leafArea, stomaDensity, stomaRadius, stomaDepth,
                            temperature, humidity, i)));
                }
                break;
        }
    }

    //Getter
    public ArrayList<Pair> getLineArray(){
        return lineArray;
    }
}
