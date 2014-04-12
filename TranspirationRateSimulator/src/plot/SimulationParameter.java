package plot;
public enum SimulationParameter {
	LeafWidth {
		public String toString() {
			return "Leaf Width (cm)";
		}
	},
	LeafArea {
		public String toString() {

			return "Leaf Area (cm²)";
		}
	},
	StomaDensity {
		public String toString() {
			return "Stoma Density (#/million�)";
		}
	},
	StomaRadius {
		public String toString() {
			return "Stoma Radius (µm)";
		}
	},
	StomaDepth {
		public String toString() {
			return "Stoma Depth (µm)";
		}
	},
	Temperature {
		public String toString() {
			return "Temperature (°C)";
		}
	},
	RelativeHumidityOfAir {
		public String toString() {
			return "Relative Humidity (%)";
		}
	},
	WindSpeed {
		public String toString() {
			return "Wind Speed (mph)";
		}
	}


}
