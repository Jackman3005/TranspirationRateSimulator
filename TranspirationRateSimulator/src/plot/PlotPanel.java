package plot;

import javafx.scene.layout.BorderPane;

public class PlotPanel extends BorderPane {
	public PlotPanel(WaterLossGraph graph) {
		
		this.setTop(graph);
		this.setCenter(new AxisParameterChooserAndLabel());
	}

}
