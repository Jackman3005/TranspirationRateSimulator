
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class Chart_without_dots extends Application {
	@Override
	public void start(Stage stage) {
		try {
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,800,600);
			
	        stage.setTitle("Fx_test");
	        //defining the axes
	        final NumberAxis xAxis = new NumberAxis();
	        final NumberAxis yAxis = new NumberAxis();
	        yAxis.setLabel("H20 Loss per Minute");
	        //creating the chart
	        final LineChart<Number,Number> lineChart = 
	                new LineChart<Number,Number>(xAxis,yAxis);
	                
	        lineChart.setTitle("Transpiration Rate Simulatoin");
	        //defining a series
	        XYChart.Series series = new XYChart.Series();
	       // series.setName("My portfolio");
	        //populating the series with data
	        series.getData().add(new XYChart.Data(1, 4));
	        series.getData().add(new XYChart.Data(4, 13));
	        series.getData().add(new XYChart.Data(7, 17));
	        series.getData().add(new XYChart.Data(10, 23));

	        
	        Scene scene  = new Scene(lineChart,800,600);
	        lineChart.setCreateSymbols(false);
	        lineChart.getData().add(series);
	       
	        stage.setScene(scene);
	        stage.show();
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setScene(scene);
			//primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
