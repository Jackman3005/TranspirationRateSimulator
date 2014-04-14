package com.transpirationRateSimulator;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AboutDialog extends Stage {

	public AboutDialog() {
		setHeight(320);
		setWidth(550);
		setResizable(false);
		setTitle("About");
		initStyle(StageStyle.UTILITY);
		GridPane aboutDialogPane = new GridPane();
		Text aboutText = new Text();
		aboutText.setStyle("-fx-text-alignment:center");
		aboutText
				.setText("Transpiration Rate Simulator\n\n"
						+ "This Program was created for Dr. Gary Hannan in the Winter 2014 Semester \n"
						+ "as part of a senior Software Engineering class at Eastern Michigan University. The values \n"
						+ "produced by the application are for theoretical simulation only and are not meant research use.\n\n"
						+ "You may thank the following students for this application:\n"
						+ "Jack Coy\n" + "Levi Barry\n" + "Junpeng Li\n" + "Zhaofei Ding\n"
						+ "Zhen Wang\n\n\n"
						+ "If you have any questions regarding this application or its upkeep\n"
						+ "feel free to contact Jack Coy at Jackman3000@gmail.com");
		GridPane.setConstraints(aboutText, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER,
				Priority.NEVER, new Insets(10));
		aboutDialogPane.getChildren().add(aboutText);

		setScene(new Scene(aboutDialogPane));
	}

}
