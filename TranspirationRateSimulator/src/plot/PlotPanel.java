package plot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.BorderPane;

public class PlotPanel extends BorderPane {
	private final class IndependentVariableListener implements
			EventHandler<ActionEvent> {
		private WaterLossGraphModel graphModel;
		private AxisParameterChooserAndLabel parameterChooser;

		public IndependentVariableListener(WaterLossGraphModel graphModel,
				AxisParameterChooserAndLabel parameterChooser) {
					this.graphModel = graphModel;
					this.parameterChooser = parameterChooser;
		}

		@Override
		public void handle(ActionEvent event) {	
			graphModel.setIndependentParameter(parameterChooser.getValue());
		}
	}

	public PlotPanel(WaterLossGraphModel graphModel) {
		
        WaterLossGraph graph = new WaterLossGraph(new NumberAxis(), new NumberAxis(),graphModel);
        AxisParameterChooserAndLabel parameterChooser = new AxisParameterChooserAndLabel();
        
        parameterChooser.setOnAction(new IndependentVariableListener(graphModel,parameterChooser));
        
		this.setTop(graph);
		this.setCenter(parameterChooser);
	}

}
