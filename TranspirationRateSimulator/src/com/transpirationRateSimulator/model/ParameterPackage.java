package com.transpirationRateSimulator.model;

import java.util.ArrayList;
import java.util.List;


public class ParameterPackage {

	private double leafWidth;
	private double leafArea;
	private double stomaDensity;
	private double stomaRadius;
	private double stomaDepth;
	private double temperature;
	private double humidity;
	private double windspeed;
	private final List<ParameterPackageObserverInterface> observers = new ArrayList<ParameterPackageObserverInterface>();

	public ParameterPackage(ParameterPackage packageToCopy) {
		for (SimulationParameter parameter : SimulationParameter.values()) {
			setParameterValue(parameter,
					packageToCopy.getParameterValue(parameter));
		}
	}

	public ParameterPackage(double leafWidth, double leafArea,
			double stomaDensity, double stomaRadius, double stomaDepth,
			double temperature, double humidity, double windspeed) {
		this.leafWidth = leafWidth;
		this.leafArea = leafArea;
		this.stomaDensity = stomaDensity;
		this.stomaRadius = stomaRadius;
		this.stomaDepth = stomaDepth;
		this.temperature = temperature;
		this.humidity = humidity;
		this.windspeed = windspeed;
	}

	public void setParameterValue(SimulationParameter parameter, double newValue) {
		switch (parameter) {
		case LeafArea:
			if (this.leafArea != newValue) {
				this.leafArea = newValue;
			} else {
				return;// Prevents notifying observers when There isn't a new
						// value
			}
			break;
		case LeafWidth:
			if (this.leafWidth != newValue) {
				this.leafWidth = newValue;
			} else {
				return;// Prevents notifying observers when There isn't a new
						// value
			}
			break;
		case RelativeHumidityOfAir:
			if (this.humidity != newValue) {
				this.humidity = newValue;
			} else {
				return;// Prevents notifying observers when There isn't a new
						// value
			}
			break;
		case StomaDensity:
			if (this.stomaDensity != newValue) {
				this.stomaDensity = newValue;
			} else {
				return;// Prevents notifying observers when There isn't a new
						// value
			}
			break;
		case StomaDepth:
			if (this.stomaDepth != newValue) {
				this.stomaDepth = newValue;
			} else {
				return;// Prevents notifying observers when There isn't a new
						// value
			}
			break;
		case StomaRadius:
			if (this.stomaRadius != newValue) {
				this.stomaRadius = newValue;
			} else {
				return;// Prevents notifying observers when There isn't a new
						// value
			}
			break;
		case Temperature:
			if (this.temperature != newValue) {
				this.temperature = newValue;
			} else {
				return;// Prevents notifying observers when There isn't a new
						// value
			}
			break;
		case WindSpeed:
			if (this.windspeed != newValue) {
				this.windspeed = newValue;
			} else {
				return;// Prevents notifying observers when There isn't a new
						// value
			}
			break;
		}
		notifyObservers();
	}

	public double getParameterValue(SimulationParameter parameter) {
		switch (parameter) {
		case LeafArea:
			return this.leafArea;
		case LeafWidth:
			return this.leafWidth;
		case RelativeHumidityOfAir:
			return this.humidity;
		case StomaDensity:
			return this.stomaDensity;
		case StomaDepth:
			return this.stomaDepth;
		case StomaRadius:
			return this.stomaRadius;
		case Temperature:
			return this.temperature;
		case WindSpeed:
			return this.windspeed;
		default:
			return -1;
		}
	}

	// This allows the object to be observable. Notifying it's observers when it
	// is updated.
	public void addObserver(ParameterPackageObserverInterface observer) {
		this.observers.add(observer);
	}

	public void removeObserver(ParameterPackageObserverInterface observer) {
		this.observers.remove(observer);
	}

	private void notifyObservers() {
		for (ParameterPackageObserverInterface observer : this.observers) {
			observer.notifyParameterValueChanged();
		}
	}
}
