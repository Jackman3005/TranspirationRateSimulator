package plot;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class WaterLossGraph extends LineChart<Number, Number> {

	public WaterLossGraph(NumberAxis xAxis, NumberAxis yAxis,
			WaterLossGraphModel graphModel) {
		super(xAxis, yAxis);
		// defining the axes
		yAxis.setLabel("H20 Loss per Minute");

		this.setTitle("Transpiration Rate Simulatoin");

		xAxis.setLabel("");

		// defining a series

		List<javafx.scene.chart.XYChart.Series<Number, Number>> allLineSeries = graphModel
				.getAllLineSeries();
		configureAxisRangesAndTickMarks(xAxis, yAxis, allLineSeries);

		this.setCreateSymbols(false);
		this.setLegendVisible(false);
		for (Series<Number, Number> series : allLineSeries) {
			this.getData().add(series);
		}

	}

	private void configureAxisRangesAndTickMarks(NumberAxis xAxis,
			NumberAxis yAxis, List<XYChart.Series<Number, Number>> allLineSeries) {
		double xMin = Double.MAX_VALUE;
		double xMax = -Double.MAX_VALUE;
		double yMin = Double.MAX_VALUE;
		double yMax = -Double.MAX_VALUE;
		for (Series<Number, Number> series : allLineSeries) {
			ObservableList<Data<Number, Number>> data = series.getData();

			for (Data<Number, Number> coordinate : data) {
				double xValue = coordinate.getXValue().doubleValue();
				double yValue = coordinate.getYValue().doubleValue();

				if (xValue > xMax) {
					xMax = xValue;
				}
				if (xValue < xMin) {
					xMin = xValue;
				}
				if (yValue > yMax) {
					yMax = yValue;
				}
				if (yValue < yMin) {
					yMin = yValue;
				}
			}
		}
		double xDataSpan = xMax - xMin;
		double yDataSpan = yMax - yMin;

		double xBuffer = Math.max(1, xDataSpan * .05);
		double yBuffer = Math.max(1, yDataSpan * .05);

		int xUpperBound = (int) (xMax + xBuffer);
		int xLowerBound = (int) (xMin - xBuffer);
		int yUpperBound = (int) (yMax + yBuffer);
		int yLowerBound = (int) (yMin - yBuffer);

		xAxis.setUpperBound(xUpperBound);
		xAxis.setLowerBound(xLowerBound);
		yAxis.setUpperBound(yUpperBound);
		yAxis.setLowerBound(yLowerBound);

		int numberOfTickMarks = 6;

		int xBoundSpan = xUpperBound - xLowerBound;
		int yBoundSpan = yUpperBound - yLowerBound;

		xAxis.setTickUnit(xBoundSpan / numberOfTickMarks);
		yAxis.setTickUnit(yBoundSpan / numberOfTickMarks);

		xAxis.setAutoRanging(false);
		yAxis.setAutoRanging(false);

	}

}
