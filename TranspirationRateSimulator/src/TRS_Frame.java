import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

        WaterLossGraph graph = new WaterLossGraph(new NumberAxis(), new NumberAxis());
        GridPane mainPane = new GridPane();
        mainPane.add(graph, 0, 0);
        
        Scene mainScene = new Scene(mainPane, 1000, 650, Color.LIGHTBLUE);
        stage.setScene(mainScene);
        stage.show();

    }
}
