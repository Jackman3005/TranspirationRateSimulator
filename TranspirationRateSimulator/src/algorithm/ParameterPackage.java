package algorithm;

public class ParameterPackage {

	private double leafWidth;
	private double leafArea;
	private double stomaDensity;
	private double stomaRadius;
	private double stomaDepth;
	private double temperature;
	private double humidity;
	private double windspeed;

	public ParameterPackage(double leafWidth, double leafArea, double stomaDensity,
            double stomaRadius, double stomaDepth, double temperature, double humidity, double windspeed) {
				this.leafWidth = leafWidth;
				this.leafArea = leafArea;
				this.stomaDensity = stomaDensity;
				this.stomaRadius = stomaRadius;
				this.stomaDepth = stomaDepth;
				this.temperature = temperature;
				this.humidity = humidity;
				this.windspeed = windspeed;
	}

	public double getLeafWidth() {
		return leafWidth;
	}

	public void setLeafWidth(double leafWidth) {
		this.leafWidth = leafWidth;
	}

	public double getLeafArea() {
		return leafArea;
	}

	public void setLeafArea(double leafArea) {
		this.leafArea = leafArea;
	}

	public double getStomaDensity() {
		return stomaDensity;
	}

	public void setStomaDensity(double stomaDensity) {
		this.stomaDensity = stomaDensity;
	}

	public double getStomaRadius() {
		return stomaRadius;
	}

	public void setStomaRadius(double stomaRadius) {
		this.stomaRadius = stomaRadius;
	}

	public double getStomaDepth() {
		return stomaDepth;
	}

	public void setStomaDepth(double stomaDepth) {
		this.stomaDepth = stomaDepth;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getWindspeed() {
		return windspeed;
	}

	public void setWindspeed(double windspeed) {
		this.windspeed = windspeed;
	}
	
	
	
	
	
}
