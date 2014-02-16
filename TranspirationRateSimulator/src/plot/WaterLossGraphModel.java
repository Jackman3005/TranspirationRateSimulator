package plot;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import java.util.ArrayList;
import java.util.List;

public class WaterLossGraphModel  {

	
	public WaterLossGraphModel() {
	}
	
	public List<XYChart.Series<Number, Number>> getAllLineSeries(){
		
		XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
        ArrayList<Pair> line = LineBuilder.getLine(SimulationParameters.LeafWidth, 1, 10, 1, 100, 200, 5, 15, 20, 50, 20);

        for (Pair p : line){
            series1.getData().add(new XYChart.Data<Number, Number>(p.getXValue(), p.getYValue()));
        }
/*		// populating the series with data
		series1.getData().add(new XYChart.Data<Number, Number>(10, 4));
		series1.getData().add(new XYChart.Data<Number, Number>(14, 13));
		series1.getData().add(new XYChart.Data<Number, Number>(17, 17));
		series1.getData().add(new XYChart.Data<Number, Number>(20, 23));
*/
		XYChart.Series<Number, Number> series2 = new XYChart.Series<Number, Number>();
		// populating the series with data
		series2.getData().add(new XYChart.Data<Number, Number>(10, 14));
		series2.getData().add(new XYChart.Data<Number, Number>(14, 11));
		series2.getData().add(new XYChart.Data<Number, Number>(17, 13));
		series2.getData().add(new XYChart.Data<Number, Number>(20, 7));

		ArrayList<Series<Number, Number>> listOfSeries = new ArrayList<XYChart.Series<Number, Number>>();
		listOfSeries.add(series1);
		//listOfSeries.add(series2);
		
		return listOfSeries;
	}
	
	
	
	
}
