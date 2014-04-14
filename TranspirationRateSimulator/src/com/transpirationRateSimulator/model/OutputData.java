package com.transpirationRateSimulator.model;

import javafx.beans.property.SimpleDoubleProperty;

public class OutputData {

	private final SimpleDoubleProperty tickMark = new SimpleDoubleProperty();
	private final SimpleDoubleProperty value = new SimpleDoubleProperty();

	public OutputData(Double parameter, Double value) {
		this.tickMark.set(parameter);
		this.value.set(value);
	}

	public SimpleDoubleProperty getValueProperty() {
		return this.value;
	}

	public Double getValue() {
		return this.value.get();
	}

	public SimpleDoubleProperty getTickMarkProperty() {
		return this.tickMark;
	}

	public Double getTickMark() {
		return this.tickMark.get();
	}
}