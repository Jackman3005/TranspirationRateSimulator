import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;
import javafx.util.Callback;
import plot.SimulationParameter;

public class InputTable extends TableView<SimulationParameter> {

	public InputTable() {
		autosize();
		final Label label = new Label("Parameter Input Table");
		label.setFont(new Font("Arial", 20));
		setEditable(true);
		setMaxHeight(219);

		TableColumn<SimulationParameter, String> parameterNameColumn = new TableColumn<SimulationParameter, String>(
				"Parameter");
		parameterNameColumn.setMinWidth(160);

		ObservableList<SimulationParameter> simulationParametersList = FXCollections
				.observableArrayList(SimulationParameter.values());

		parameterNameColumn
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SimulationParameter, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							CellDataFeatures<SimulationParameter, String> param) {
						return new SimpleStringProperty(param.getValue()
								.toString());
					}
				});

		TableColumn<SimulationParameter, Double> line1Parameters = new TableColumn<SimulationParameter, Double>(
				"Line 1");
		line1Parameters.setMinWidth(87);
		// line1Parameters
		// .setCellValueFactory(new
		// Callback<TableColumn.CellDataFeatures<SimulationParameter, Double>,
		// ObservableValue<Double>>() {
		//
		// @Override
		// public ObservableValue<Double> call(
		// CellDataFeatures<SimulationParameter, Double> param) {
		//
		// return new SimpleDoubleProperty();
		// }
		// });

		setItems(simulationParametersList);
		getColumns().addAll(parameterNameColumn, line1Parameters);
		autosize();

	}
}