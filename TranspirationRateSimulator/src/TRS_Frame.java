import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import plot.PlotPanel;
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
        mainPane.add(new PlotPanel(new WaterLossGraphModel()), 0, 0);
        mainPane.add(new InputTable(stage), 1, 0);
        
        Scene mainScene = new Scene(mainPane, 1000, 650, Color.LIGHTBLUE);
        stage.setScene(mainScene);
        stage.show();

    }
}
