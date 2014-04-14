package plot;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import algorithm.LineBuilder;
import algorithm.ParameterPackage;
import algorithm.ParameterPackageObserverInterface;

public class WaterLossGraphModel {

	private final class ParameterPackageObserver implements
			ParameterPackageObserverInterface {
		@Override
		public void notifyParameterValueChanged() {
			notifyObserversModelUpdated();
		}
	}

	private SimulationParameter independentParameter = SimulationParameter.LeafWidth;
	private final List<WaterLossGraphModelObserverInterface> observers = new ArrayList<>();
	private double rangeMinimum = 1;
	private double rangeMaximum = 100;
	private double tickMarkIncrement = (this.rangeMaximum - this.rangeMinimum) / 6.0;
	private List<ParameterPackage> parameterPackages = new ArrayList<>();

	ParameterPackage DEFAULT_PARAMETER_PACKAGE = new ParameterPackage(1, 100,
			200, 5, 15, 20, 50, 20);

	private final ParameterPackageObserverInterface PARAMETER_PACKAGE_OBSERVER = new ParameterPackageObserver();

	public WaterLossGraphModel() {
		this.parameterPackages.add(this.DEFAULT_PARAMETER_PACKAGE);
		this.DEFAULT_PARAMETER_PACKAGE
				.addObserver(this.PARAMETER_PACKAGE_OBSERVER);
	}

	public List<XYChart.Series<Number, Number>> getAllLineSeriesForPlotting() {
		double incrementBetweenDataPoints = (this.rangeMaximum - this.rangeMinimum) / 200;

		ArrayList<Series<Number, Number>> listOfLineSeriesForPlotting = new ArrayList<XYChart.Series<Number, Number>>();

		for (List<Pair> list : getDataPointsForAllLines(incrementBetweenDataPoints)) {
			Series<Number, Number> series = new Series<Number, Number>();
			for (Pair p : list) {
				series.getData().add(
						new XYChart.Data<Number, Number>(p.getXValue(), p
								.getYValue()));
			}
			listOfLineSeriesForPlotting.add(series);
		}
		return listOfLineSeriesForPlotting;
	}

	private List<List<Pair>> getDataPointsForAllLines(
			double incrementBetweenDataPoints) {

		ArrayList<List<Pair>> listOfLineData = new ArrayList<List<Pair>>();
		for (ParameterPackage setOfParameters : this.parameterPackages) {
			ArrayList<Pair> line = LineBuilder.getLine(
					this.independentParameter, this.rangeMinimum,
					this.rangeMaximum, setOfParameters,
					incrementBetweenDataPoints);
			listOfLineData.add(line);
		}

		return listOfLineData;
	}

	public void setParameterPackages(List<ParameterPackage> parameterPackages) {
		for (ParameterPackage parameterPackage : this.parameterPackages) {
			parameterPackage.removeObserver(this.PARAMETER_PACKAGE_OBSERVER);
		}
		this.parameterPackages = parameterPackages;
		for (ParameterPackage parameterPackage : this.parameterPackages) {
			parameterPackage.addObserver(this.PARAMETER_PACKAGE_OBSERVER);
		}
	}

	public List<ParameterPackage> getParameterPackages() {
		return this.parameterPackages;
	}

	public void setIndependentParameter(SimulationParameter parameter) {
		this.independentParameter = parameter;
		notifyObserversModelUpdated();
	}

	public void addObserver(WaterLossGraphModelObserverInterface observer) {
		this.observers.add(observer);
	}

	public void removeObserver(WaterLossGraphModelObserverInterface observer) {
		this.observers.remove(observer);
	}

	private void notifyObserversModelUpdated() {
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
		notifyObserversModelUpdated();
	}

	public SimulationParameter getIndependentVariable() {
		return this.independentParameter;
	}

	public List<List<OutputData>> getOutputTableData() {
		List<List<Pair>> allLineSeries = getDataPointsForAllLines(this.tickMarkIncrement);

		List<List<OutputData>> outputDataForAllLines = new ArrayList<>();
		for (List<Pair> lineData : allLineSeries) {
			ArrayList<OutputData> outputData = new ArrayList<OutputData>();
			for (Pair pair : lineData) {
				outputData.add(new OutputData(pair.getXValue(), (Math
						.round(pair.getYValue() * 1000.0) / 1000.0)));
			}
			outputDataForAllLines.add(outputData);
		}

		return outputDataForAllLines;
	}
}