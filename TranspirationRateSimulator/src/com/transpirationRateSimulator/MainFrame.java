package com.transpirationRateSimulator;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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

		WaterLossGraphModel graphModel = new WaterLossGraphModel();

		MenuBar menuBar = createMenuBar(graphModel);
		PlotPanel plotPanel = new PlotPanel(graphModel);
		ParameterInputTable parameterInputTable = new ParameterInputTable(graphModel);
		OutputTable outputTable = new OutputTable(graphModel);
		Leaf leaf = new Leaf();

		GridPane.setConstraints(menuBar, 0, 0, 2, 1, HPos.LEFT, VPos.TOP, Priority.ALWAYS,
				Priority.NEVER, new Insets(0));
		GridPane.setConstraints(plotPanel, 1, 2, 1, 1, HPos.LEFT, VPos.TOP, Priority.ALWAYS,
				Priority.ALWAYS, new Insets(15));
		GridPane.setConstraints(parameterInputTable, 0, 1, 1, 1, HPos.LEFT, VPos.TOP,
				Priority.ALWAYS, Priority.ALWAYS, new Insets(15));
		GridPane.setConstraints(outputTable, 0, 2, 1, 1, HPos.LEFT, VPos.TOP, Priority.ALWAYS,
				Priority.ALWAYS, new Insets(15));
		GridPane.setConstraints(leaf, 1, 1, 1, 1, HPos.RIGHT, VPos.TOP, Priority.NEVER,
				Priority.NEVER, new Insets(15));

		mainPane.getChildren().addAll(menuBar, plotPanel, parameterInputTable, outputTable, leaf);

		Scene mainScene = new Scene(mainPane, Color.LIGHTBLUE);

		mainScene.getStylesheets().add(
				WaterLossGraph.class.getResource("WaterLossGraphModel.css").toExternalForm());

		return mainScene;
	}

	private MenuBar createMenuBar(WaterLossGraphModel model) {
		MenuBar menuBar = new MenuBar();
		Menu aboutMenu = new AboutMenu();
		FileMenu fileMenu = new FileMenu(model);
		menuBar.getMenus().addAll(fileMenu, aboutMenu);
		return menuBar;
	}
}
