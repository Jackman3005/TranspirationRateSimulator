
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @author: Junpeng Li
 * @version: 3/26/14
 */

public class Leaf {

	private static final int STAR_COUNT = 100;

	static private final Circle[] nodes = new Circle[STAR_COUNT];
	private final double[] angles = new double[STAR_COUNT];
	private final long[] start = new long[STAR_COUNT];

	private final double[] width = new double[STAR_COUNT];
	private final double[] height = new double[STAR_COUNT];

	private final Random random = new Random();

	private final int CP_width = 260;
	private final int CP_height = 300;

	public Pane play(final Stage primaryStage) {
		Pane canvas = new Pane();

		Group root = new Group();

		Group circles = new Group();

		for (int i = 0; i < STAR_COUNT; i++) {
			nodes[i] = new Circle(5, Color.CYAN);
			this.angles[i] = 0.7 * Math.PI * this.random.nextDouble() + Math.PI
					/ 6;
			this.start[i] = this.random.nextInt(2000000000);

			this.width[i] = this.CP_width + (Math.random() - 0.5) * 100;
			this.height[i] = this.CP_height + (Math.random() - 0.5) * 100;

			circles.getChildren().add(nodes[i]);
		}
		root.getChildren().add(circles);

		canvas.getChildren().add(root);

		// final Scene scene = new Scene(canvas, 620, 350, Color.BLACK);
		// scene.getStylesheets().add(TRS_Frame.class.getResource("application.css").toExternalForm());

		String image = TRS_Frame.class.getResource("leaf.gif").toExternalForm();
		canvas.setStyle("-fx-background-image: url('"
				+ image
				+ "'); -fx-background-position: center center;  -fx-background-repeat: no-repeat;");

		// primaryStage.setScene(scene);
		// primaryStage.show();
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				// final double width = 0.5 * primaryStage.getWidth();
				// final double height = 0.2 * primaryStage.getHeight();
				// final double radius = Math.sqrt(2) *
				// Math.max(0.1*primaryStage.getWidth(),0.1*primaryStage.getHeight());
				final double radius = 70;
				for (int i = 0; i < STAR_COUNT; i++) {
					final Node node = nodes[i];
					final double angle = Leaf.this.angles[i];
					final long t = (now - Leaf.this.start[i]) % 2000000000;
					final double d = t * radius / 2000000000.0;
					node.setTranslateX(Math.cos(angle) * d + Leaf.this.width[i]);
					node.setTranslateY(Math.sin(angle) * d
							+ Leaf.this.height[i]);
				}
			}
		}.start();
		return canvas;
	}

}
