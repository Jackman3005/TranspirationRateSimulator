package com.transpirationRateSimulator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import com.transpirationRateSimulator.model.WaterLossGraphModel;

public class FileMenu extends Menu {
	public FileMenu(final WaterLossGraphModel model) {
		setText("File");

		MenuItem resetToDefaults = new MenuItem();
		resetToDefaults.setText("Reset To Defaults");
		resetToDefaults.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				model.resetAllValuesToDefaults();
			}
		});

		getItems().add(resetToDefaults);
	}
}
