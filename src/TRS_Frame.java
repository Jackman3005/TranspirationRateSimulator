import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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

        Button btn = new Button();
        btn.setText("New Scene");

        final Group root = new Group();
        Scene openScene = new Scene(root, 1000, 650, Color.LIGHTBLUE);
        root.getChildren().add(btn);
        stage.setScene(openScene);
        stage.show();

        final Scene scene2 = new Scene(new Group(new Text(500, 500, "Hello")));

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene2);
            }
        });

    }
}
