package com.transpirationRateSimulator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.transpirationRateSimulator.model.WaterLossGraphModel;
import com.transpirationRateSimulator.plot.PlotPanel;
import com.transpirationRateSimulator.plot.WaterLossGraph;
import com.transpirationRateSimulator.tables.OutputTable;
import com.transpirationRateSimulator.tables.ParameterInputTable;

public class MainFrame extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {

		stage.setTitle("Transpiration Rate Simulator");

		Scene mainScene = createMainScene(stage);

		stage.setScene(mainScene);
		stage.show();

	}

	private Scene createMainScene(final Stage stage) {
		GridPane mainPane = new GridPane();

		mainPane.setVgap(25);
		mainPane.setHgap(25);
		mainPane.setPadding(new Insets(0, 15, 0, 15));
		mainPane.getColumnConstraints().add(new ColumnConstraints(450));
		mainPane.getColumnConstraints().add(new ColumnConstraints(450));
		mainPane.getRowConstraints().add(new RowConstraints(10));
		mainPane.getRowConstraints().add(new RowConstraints(400));
		mainPane.getRowConstraints().add(new RowConstraints(200));
		MenuBar menuBar = new MenuBar();
		Menu aboutMenu = new AboutMenu();
		menuBar.getMenus().add(aboutMenu);
		mainPane.add(menuBar, 0, 0);

		WaterLossGraphModel graphModel = new WaterLossGraphModel();
		mainPane.add(new PlotPanel(graphModel), 0, 1);
		mainPane.add(new ParameterInputTable(graphModel), 1, 1);
		mainPane.add(new OutputTable(graphModel), 0, 2);
		mainPane.add(new Leaf().play(stage), 1, 2);

		Scene mainScene = new Scene(mainPane, 1000, 650, Color.LIGHTBLUE);

		mainScene.getStylesheets().add(
				WaterLossGraph.class.getResource("WaterLossGraphModel.css")
						.toExternalForm());

		return mainScene;
	}
}
