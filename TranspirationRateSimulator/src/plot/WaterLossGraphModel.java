package plot;

import algorithm.LineBuilder;
import algorithm.ParameterPackage;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import java.util.ArrayList;
import java.util.List;

public class WaterLossGraphModel {

	private SimulationParameter independentParameter = SimulationParameter.LeafWidth;
	private final List<WaterLossGraphModelObserverInterface> observers = new ArrayList<>();
	private double rangeMinimum = 1;
	private double rangeMaximum = 100;
	private double tickMarkIncrement = (this.rangeMaximum - this.rangeMinimum) / 6.0;

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

		ParameterPackage parameterPackage = new ParameterPackage(1, 100, 200,
				5, 15, 20, 50, 20);
        ParameterPackage parameterPackage2 = new ParameterPackage(1, 100, 200,
                15, 20, 30, 50, 20);
		ArrayList<ParameterPackage> listOfUserEnteredParameterPackages = new ArrayList<ParameterPackage>();
		listOfUserEnteredParameterPackages.add(parameterPackage);
        listOfUserEnteredParameterPackages.add(parameterPackage2);


        ArrayList<List<Pair>> listOfLineData = new ArrayList<List<Pair>>();
		for (ParameterPackage setOfParameters : listOfUserEnteredParameterPackages) {
			ArrayList<Pair> line = LineBuilder.getLine(
					this.independentParameter, this.rangeMinimum,
					this.rangeMaximum, setOfParameters,
					incrementBetweenDataPoints);
			listOfLineData.add(line);
		}

		return listOfLineData;
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

	public SimulationParameter getIndependentVariable() {
		return this.independentParameter;
	}

	public List<List<OutputData>> getOutputTableData() {
		List<List<Pair>> allLineSeries = getDataPointsForAllLines(this.tickMarkIncrement);

		List<List<OutputData>> outputDataForAllLines = new ArrayList<>();
		for (List<Pair> lineData : allLineSeries) {
			ArrayList<OutputData> outputData = new ArrayList<OutputData>();
			for (Pair pair : lineData) {
				outputData.add(new OutputData(pair.getXValue(),
                        (Math.round(pair.getYValue() *1000.0) / 1000.0)));
			}
			outputDataForAllLines.add(outputData);
		}

		return outputDataForAllLines;
	}
}