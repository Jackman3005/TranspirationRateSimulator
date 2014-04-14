package com.transpirationRateSimulator;

import javafx.scene.layout.Pane;

public class Leaf extends Pane {
	public Leaf() {
		setMinWidth(425);
		setMinHeight(191);
		String image = MainFrame.class.getResource("leafCrossSection.jpg")
				.toExternalForm();
		setStyle("-fx-background-image: url('"
				+ image
				+ "'); -fx-background-position: center center;  -fx-background-repeat: no-repeat;");
	}

}
