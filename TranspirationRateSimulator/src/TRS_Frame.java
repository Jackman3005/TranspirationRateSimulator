import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
        mainPane.getColumnConstraints().add(new ColumnConstraints(600)); 
        mainPane.getColumnConstraints().add(new ColumnConstraints(500)); 
        mainPane.getRowConstraints().add(new RowConstraints(450)); 
        mainPane.getRowConstraints().add(new RowConstraints(500)); 
        
        mainPane.add(new PlotPanel(new WaterLossGraphModel()), 0, 0);
        mainPane.add(new InputTable(), 1, 0);
        mainPane.add(new TheOutputTable(), 0, 1);
	    mainPane.add(new Leaf().play(stage),1,1);
        
        Scene mainScene = new Scene(mainPane, 1000, 650, Color.LIGHTBLUE);
        stage.setScene(mainScene);
        stage.show();

    }
}
