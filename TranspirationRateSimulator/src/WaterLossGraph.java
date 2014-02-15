
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class WaterLossGraph extends LineChart<Number, Number> {
	
	public WaterLossGraph(NumberAxis xAxis, NumberAxis yAxis) {
		super(xAxis, yAxis);
	        //defining the axes
	        yAxis.setLabel("H20 Loss per Minute");
	                
	        this.setTitle("Transpiration Rate Simulatoin");
	        
	        xAxis.setLabel("ChangableLabel");
	        //defining a series
	        XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();

	        //populating the series with data
	        series.getData().add(new XYChart.Data<Number,Number>(10, 4));
	        series.getData().add(new XYChart.Data<Number,Number>(14, 13));
	        series.getData().add(new XYChart.Data<Number,Number>(17, 17));
	        series.getData().add(new XYChart.Data<Number,Number>(20, 23));
	        
	        
	        this.setCreateSymbols(false);
	        this.setLegendVisible(false);
	        this.getData().add(series);
			
	}
	
}
