/**
 * @author: Zhaofei
 * @version: 3/1/14
 */

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import plot.OutputData;
import plot.WaterLossGraphModel;
import plot.WaterLossGraphModelObserverInterface;

import java.util.List;

public class OutputTable extends TableView<OutputData> {

	private final class TableUpdatingModelObserver implements
			WaterLossGraphModelObserverInterface {
		@Override
		public void graphModelHasChanged() {
			rebuildTable();
		}
	}

	private final WaterLossGraphModel model;

	public OutputTable(WaterLossGraphModel model) {
		model.addObserver(new TableUpdatingModelObserver());
		this.model = model;
		maxHeightProperty().set(250);

		rebuildTable();
	}

	private void rebuildTable() {

		TableColumn<OutputData, Double> tickMarkValuesColumn = new TableColumn<OutputData, Double>(
				this.model.getIndependentVariable().toString());
		tickMarkValuesColumn
				.setCellValueFactory(new PropertyValueFactory<OutputData, Double>(
						"tickMark"));
		tickMarkValuesColumn.setMinWidth(150);
		getColumns().clear();
		getColumns().add(tickMarkValuesColumn);

		List<List<OutputData>> outputTableData = this.model
				.getOutputTableData();

		for (int i = 1; i <= outputTableData.size(); i++) {
			TableColumn<OutputData, Double> lineValuesAtTickMarksColumn = new TableColumn<OutputData, Double>(
					"Line " + i);
			lineValuesAtTickMarksColumn
					.setCellValueFactory(new PropertyValueFactory<OutputData, Double>(
							"value"));
			lineValuesAtTickMarksColumn.setMinWidth(100);
			getColumns().add(lineValuesAtTickMarksColumn);
		}

		setItems(new ObservableListWrapper<OutputData>(outputTableData.get(0)));

	}
}