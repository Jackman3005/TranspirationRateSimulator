package com.transpirationRateSimulator.plot;

import com.transpirationRateSimulator.model.WaterLossGraphModel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AxisSettingsButton extends Button {

	private final class ShowAxisSettingsDialogActionListener implements
			EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			AxisSettingsDialog dialog = new AxisSettingsDialog(
					AxisSettingsButton.this.graphModel);
			dialog.show();
		}
	}

	private final WaterLossGraphModel graphModel;

	public AxisSettingsButton(WaterLossGraphModel graphModel) {
		ImageView plotSettingsImageNode = new ImageView();

		plotSettingsImageNode.setImage(new Image(getClass()
				.getResourceAsStream("graphSettings.png")));

		setGraphic(plotSettingsImageNode);
		setText("Axis Settings");

		this.graphModel = graphModel;

		setOnAction(new ShowAxisSettingsDialogActionListener());
	}
}
