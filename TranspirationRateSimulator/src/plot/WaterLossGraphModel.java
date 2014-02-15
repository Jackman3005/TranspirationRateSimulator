package plot;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class WaterLossGraphModel  {

	
	public WaterLossGraphModel() {
	}
	
	public List<XYChart.Series<Number, Number>> getAllLineSeries(){
		
		XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
		// populating the series with data
		series1.getData().add(new XYChart.Data<Number, Number>(10, 4));
		series1.getData().add(new XYChart.Data<Number, Number>(14, 13));
		series1.getData().add(new XYChart.Data<Number, Number>(17, 17));
		series1.getData().add(new XYChart.Data<Number, Number>(20, 23));

		XYChart.Series<Number, Number> series2 = new XYChart.Series<Number, Number>();
		// populating the series with data
		series2.getData().add(new XYChart.Data<Number, Number>(10, 14));
		series2.getData().add(new XYChart.Data<Number, Number>(14, 11));
		series2.getData().add(new XYChart.Data<Number, Number>(17, 13));
		series2.getData().add(new XYChart.Data<Number, Number>(20, 7));
		
		ArrayList<Series<Number, Number>> listOfSeries = new ArrayList<XYChart.Series<Number, Number>>();
		listOfSeries.add(series1);
		listOfSeries.add(series2);
		
		return listOfSeries;
	}
	
	
}
