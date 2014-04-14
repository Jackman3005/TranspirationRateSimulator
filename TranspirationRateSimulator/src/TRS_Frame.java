import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import plot.PlotPanel;
import plot.WaterLossGraph;
import plot.WaterLossGraphModel;

/**
 * @author: Levi
 * @version: 2/1/14
 */

public class TRS_Frame extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("Transpiration Rate Simulator");

		GridPane mainPane = new GridPane();
		mainPane.setVgap(25);
		mainPane.setHgap(25);
		mainPane.setPadding(new Insets(15));
		mainPane.getColumnConstraints().add(new ColumnConstraints(450));
		mainPane.getColumnConstraints().add(new ColumnConstraints(450));
		mainPane.getRowConstraints().add(new RowConstraints(400));
		mainPane.getRowConstraints().add(new RowConstraints(200));

		WaterLossGraphModel graphModel = new WaterLossGraphModel();
		mainPane.add(new PlotPanel(graphModel), 0, 0);
		mainPane.add(new InputTable(graphModel), 1, 0);
		mainPane.add(new OutputTable(graphModel), 0, 1);
		mainPane.add(new Leaf().play(stage), 1, 1);

		Scene mainScene = new Scene(mainPane, 1000, 650, Color.LIGHTBLUE);
		mainScene.getStylesheets().add(
				WaterLossGraph.class.getResource("WaterLossGraphModel.css")
						.toExternalForm());

		stage.setScene(mainScene);
		stage.show();

	}
}
