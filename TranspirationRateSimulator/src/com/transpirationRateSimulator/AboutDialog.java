package com.transpirationRateSimulator;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AboutDialog extends Stage {

	public AboutDialog() {
		setHeight(150);
		setWidth(300);
		setTitle("About");
		initStyle(StageStyle.UTILITY);
		GridPane aboutDialogPane = new GridPane();
		Text aboutText = new Text();
		aboutText.setText("Prgoram pro\nduced by:.alsdfasdf");
		aboutDialogPane.add(aboutText, 0, 0);

		setScene(new Scene(aboutDialogPane));
	}

}
