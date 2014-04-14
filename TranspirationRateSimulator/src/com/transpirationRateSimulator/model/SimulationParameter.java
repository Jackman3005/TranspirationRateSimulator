package com.transpirationRateSimulator.model;

public enum SimulationParameter {
	LeafWidth("Leaf Width (cm)", 105), LeafArea("Leaf Area (cm²)", 100), StomaDensity(
			"Stoma Density (#/million²)", 160), StomaRadius("Stoma Radius (µm)", 120), StomaDepth(
			"Stoma Depth (µm)", 120), Temperature("Temperature (c)", 100), RelativeHumidityOfAir(
			"Relative Humidity (%)", 135), WindSpeed("Wind Speed (mph)", 120);

	private final String toStringValue;
	private final double columnWidth;

	private SimulationParameter(String toStringValue, double columnWidth) {
		this.toStringValue = toStringValue;
		this.columnWidth = columnWidth;
	}

	@Override
	public String toString() {
		return this.toStringValue;
	}

	public double getPreferredColumnWidth() {
		return this.columnWidth;
	}
}
