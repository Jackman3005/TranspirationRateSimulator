package plot;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class WaterLossGraph extends LineChart<Number, Number> {

	private final WaterLossGraphModel graphModel;
	private final NumberAxis xAxis2;
	private final NumberAxis yAxis2;

	private final class RedrawLinesObserver implements
			WaterLossGraphModelObserverInterface {
		@Override
		public void graphModelHasChanged() {
			plotLines();
		}
	}

	public WaterLossGraph(NumberAxis xAxis, NumberAxis yAxis,
			WaterLossGraphModel graphModel) {
		super(xAxis, yAxis);
		this.xAxis2 = xAxis;
		this.yAxis2 = yAxis;
		this.graphModel = graphModel;
		setCreateSymbols(false);
		setLegendVisible(false);

		yAxis.setLabel("H20 Loss per Minute");
		setTitle("Transpiration Rate Simulation");
		xAxis.setLabel("");

		graphModel.addObserver(new RedrawLinesObserver());

		plotLines();
	}

	private void plotLines() {
		getData().clear();
		List<XYChart.Series<Number, Number>> allLineSeries = this.graphModel
				.getAllLineSeriesForPlotting();

		configureAxisRangesAndTickMarks(this.xAxis2, this.yAxis2, allLineSeries);
		for (Series<Number, Number> series : allLineSeries) {
			getData().add(series);
		}
	}

	private void configureAxisRangesAndTickMarks(NumberAxis xAxis,
			NumberAxis yAxis, List<XYChart.Series<Number, Number>> allLineSeries) {
		double yMin = Double.MAX_VALUE;
		double yMax = -Double.MAX_VALUE;
		for (Series<Number, Number> series : allLineSeries) {
			ObservableList<Data<Number, Number>> data = series.getData();

			for (Data<Number, Number> coordinate : data) {
				double yValue = coordinate.getYValue().doubleValue();

				if (yValue > yMax) {
					yMax = yValue;
				}
				if (yValue < yMin) {
					yMin = yValue;
				}
			}
		}
		double yDataSpan = yMax - yMin;

		double yBuffer = Math.max(1, yDataSpan * .05);

		int yUpperBound = (int) (yMax + yBuffer);
		int yLowerBound = (int) (yMin - yBuffer);

		yAxis.setUpperBound(yUpperBound);
		yAxis.setLowerBound(yLowerBound);

		int numberOfTickMarks = 6;

		int yBoundSpan = yUpperBound - yLowerBound;

		xAxis.setTickUnit(this.graphModel.getTickUnitSize());
		yAxis.setTickUnit(yBoundSpan / numberOfTickMarks);
		xAxis.setUpperBound(this.graphModel.getXAxisRangeMaximum());
		xAxis.setLowerBound(this.graphModel.getXAxisRangeMinimum());

		xAxis.setAutoRanging(false);
		yAxis.setAutoRanging(false);

	}

}
