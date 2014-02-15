package plot;
public enum SimulationParameters {
	LeafWidth {
		public String toString() {
			return "Leaf Width (cm)";
		}
	},
	LeafArea {
		public String toString() {
			return "Leaf Area (cm�)";
		}
	},
	StomaDensity {
		public String toString() {
			return "Stoma Density (#/million�)";
		}
	},
	StomaRadius {
		public String toString() {
			return "Stoma Radius (�m)";
		}
	},
	StomaDepth {
		public String toString() {
			return "Stoma Depth (�m)";
		}
	},
	Temperature {
		public String toString() {
			return "Temperature (c)";
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
	};


}
