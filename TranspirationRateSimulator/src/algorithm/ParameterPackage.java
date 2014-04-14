package algorithm;

import java.util.ArrayList;
import java.util.List;

import plot.SimulationParameter;

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
				return;
			}
			break;
		case LeafWidth:
			if (this.leafWidth != newValue) {
				this.leafWidth = newValue;
			} else {
				return;
			}
			break;
		case RelativeHumidityOfAir:
			if (this.humidity != newValue) {
				this.humidity = newValue;
			} else {
				return;
			}
			break;
		case StomaDensity:
			if (this.stomaDensity != newValue) {
				this.stomaDensity = newValue;
			} else {
				return;
			}
			break;
		case StomaDepth:
			if (this.stomaDepth != newValue) {
				this.stomaDepth = newValue;
			} else {
				return;
			}
			break;
		case StomaRadius:
			if (this.stomaRadius != newValue) {
				this.stomaRadius = newValue;
			} else {
				return;
			}
			break;
		case Temperature:
			if (this.temperature != newValue) {
				this.temperature = newValue;
			} else {
				return;
			}
			break;
		case WindSpeed:
			if (this.windspeed != newValue) {
				this.windspeed = newValue;
			} else {
				return;
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
		}

		return -1;
	}

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
