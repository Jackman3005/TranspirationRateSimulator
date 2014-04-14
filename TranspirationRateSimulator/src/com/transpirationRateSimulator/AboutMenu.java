package com.transpirationRateSimulator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class AboutMenu extends Menu {

	public AboutMenu() {
		setText("About");
		MenuItem aboutTheCreators = new MenuItem();
		aboutTheCreators.setText("About The Creators");
		getItems().add(aboutTheCreators);
		aboutTheCreators.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new AboutDialog().show();
			}
		});
	}

}
