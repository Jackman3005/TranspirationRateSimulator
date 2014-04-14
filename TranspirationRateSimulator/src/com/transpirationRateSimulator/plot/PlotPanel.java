package com.transpirationRateSimulator.plot;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.BorderPane;

import com.transpirationRateSimulator.model.WaterLossGraphModel;

public class PlotPanel extends BorderPane {
	private final class IndependentVariableListener implements EventHandler<ActionEvent> {
		private final WaterLossGraphModel graphModel;
		private final AxisParameterChooserAndLabel parameterChooser;

		public IndependentVariableListener(WaterLossGraphModel graphModel,
				AxisParameterChooserAndLabel parameterChooser) {
			this.graphModel = graphModel;
			this.parameterChooser = parameterChooser;
		}

		@Override
		public void handle(ActionEvent event) {
			this.graphModel.setIndependentParameter(this.parameterChooser.getValue());
		}
	}

	public PlotPanel(WaterLossGraphModel graphModel) {

		WaterLossGraph graph = new WaterLossGraph(new NumberAxis(), new NumberAxis(), graphModel);
		AxisParameterChooserAndLabel parameterChooser = new AxisParameterChooserAndLabel();

		parameterChooser.setOnAction(new IndependentVariableListener(graphModel, parameterChooser));
		AxisSettingsButton axisSettingsButton = new AxisSettingsButton(graphModel);
		BorderPane.setMargin(parameterChooser, new Insets(0, 0, 15, 0));
		setTop(graph);
		setCenter(parameterChooser);
		setLeft(axisSettingsButton);
	}

}
