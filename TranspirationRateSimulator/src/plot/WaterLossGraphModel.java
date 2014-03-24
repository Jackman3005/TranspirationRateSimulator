package plot;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import algorithm.LineBuilder;
import algorithm.ParameterPackage;

public class WaterLossGraphModel {

	private SimulationParameter independentParameter = SimulationParameter.LeafWidth;
	private final List<WaterLossGraphModelObserverInterface> observers = new ArrayList<>();
	private double rangeMinimum = 1;
	private double rangeMaximum = 100;
	private double tickMarkIncrement = (this.rangeMaximum - this.rangeMinimum) / 6.0;

	public List<XYChart.Series<Number, Number>> getAllLineSeries() {
		ArrayList<Series<Number, Number>> listOfSeries = new ArrayList<XYChart.Series<Number, Number>>();

		XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
		ParameterPackage parameterPackage = new ParameterPackage(1, 100, 200,
				5, 15, 20, 50, 20);
		ArrayList<Pair> line = LineBuilder.getLine(this.independentParameter,
				this.rangeMinimum, this.rangeMaximum, parameterPackage);

		for (Pair p : line) {
			series1.getData().add(
					new XYChart.Data<Number, Number>(p.getXValue(), p
							.getYValue()));
		}

		listOfSeries.add(series1);

		return listOfSeries;
	}

	public void setIndependentParameter(SimulationParameter parameter) {
		this.independentParameter = parameter;
		notifyObserver();
	}

	public void addObserver(WaterLossGraphModelObserverInterface observer) {
		this.observers.add(observer);
	}

	public void removeObserver(WaterLossGraphModelObserverInterface observer) {
		this.observers.remove(observer);
	}

	private void notifyObserver() {
		for (WaterLossGraphModelObserverInterface observer : this.observers) {
			observer.graphModelHasChanged();
		}
	}

	public double getXAxisRangeMaximum() {
		return this.rangeMaximum;
	}

	public double getXAxisRangeMinimum() {
		return this.rangeMinimum;
	}

	public double getTickUnitSize() {
		return this.tickMarkIncrement;
	}

	public void setXAxisRangeAndTickUnit(double rangeMin, double rangeMax,
			double tickMarkIncrement) {
		this.tickMarkIncrement = tickMarkIncrement;
		this.rangeMinimum = rangeMin;
		this.rangeMaximum = rangeMax;
		notifyObserver();
	}

}