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
			return "Leaf Area (cm²)";
		}
	},
	StomaDensity {
		@Override
		public String toString() {
			return "Stoma Density (#/million²)";
		}
	},
	StomaRadius {
		@Override
		public String toString() {
			return "Stoma Radius (µm)";
		}
	},
	StomaDepth {
		@Override
		public String toString() {
			return "Stoma Depth (µm)";
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
