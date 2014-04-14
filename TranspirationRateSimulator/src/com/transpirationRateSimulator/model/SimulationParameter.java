package com.transpirationRateSimulator.model;

public enum SimulationParameter {
	LeafWidth {
		@Override
		public String toString() {
			return "Leaf Width (cm)";
		}
	},
	LeafArea {
		@Override
		public String toString() {
			return "Leaf Area (cm�)";
		}
	},
	StomaDensity {
		@Override
		public String toString() {
			return "Stoma Density (#/million�)";
		}
	},
	StomaRadius {
		@Override
		public String toString() {
			return "Stoma Radius (�m)";
		}
	},
	StomaDepth {
		@Override
		public String toString() {
			return "Stoma Depth (�m)";
		}
	},
	Temperature {
		@Override
		public String toString() {
			return "Temperature (c)";
		}
	},
	RelativeHumidityOfAir {
		@Override
		public String toString() {
			return "Relative Humidity (%)";
		}
	},
	WindSpeed {
		@Override
		public String toString() {
			return "Wind Speed (mph)";
		}
	};

}
