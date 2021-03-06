package com.transpirationRateSimulator.tables;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import com.transpirationRateSimulator.model.OutputData;
import com.transpirationRateSimulator.model.SimulationParameter;
import com.transpirationRateSimulator.model.WaterLossGraphModel;
import com.transpirationRateSimulator.model.WaterLossGraphModelObserverInterface;

public class OutputTable extends TableView<ObservableList<OutputData>> {

	private final class TableUpdatingModelObserver implements WaterLossGraphModelObserverInterface {
		@Override
		public void graphModelHasChanged() {
			rebuildTable();
		}

		@Override
		public void inputDataChanged() {
			rebuildTable();
		}
	}

	private ObservableList<ObservableList<OutputData>> data;

	private final WaterLossGraphModel model;

	public OutputTable(WaterLossGraphModel model) {
		this.model = model;
		this.model.addObserver(new TableUpdatingModelObserver());

		rebuildTable();
	}

	public void rebuildTable() {
		getColumns().clear();
		this.data = FXCollections.observableArrayList();

		List<List<OutputData>> listOfColumnData = this.model.getOutputTableData();

		try {

			SimulationParameter independentVariable = this.model.getIndependentVariable();
			TableColumn<ObservableList<OutputData>, String> tickMarkValueColumn = new TableColumn<ObservableList<OutputData>, String>(
					independentVariable.toString());
			tickMarkValueColumn.setMinWidth(independentVariable.getPreferredColumnWidth());
			tickMarkValueColumn
					.setCellValueFactory(new Callback<CellDataFeatures<ObservableList<OutputData>, String>, ObservableValue<String>>() {

						@Override
						public ObservableValue<String> call(
								CellDataFeatures<ObservableList<OutputData>, String> param) {
							return new SimpleStringProperty(param.getValue().get(0).getTickMark()
									+ "");
						}
					});
			getColumns().add(tickMarkValueColumn);

			for (int i = 0; i < listOfColumnData.size(); i++) {
				final int j = i;
				TableColumn<ObservableList<OutputData>, String> valueColumn = new TableColumn<ObservableList<OutputData>, String>(
						"Line " + (i + 1));
				valueColumn
						.setCellValueFactory(new Callback<CellDataFeatures<ObservableList<OutputData>, String>, ObservableValue<String>>() {
							@Override
							public ObservableValue<String> call(
									CellDataFeatures<ObservableList<OutputData>, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).getValue()
										+ "");
							}
						});
				getColumns().add(valueColumn);
			}
			if (listOfColumnData.size() > 0) {
				for (int rowCount = 0; rowCount < listOfColumnData.get(0).size(); rowCount++) {

					ObservableList<OutputData> row = FXCollections.observableArrayList();

					for (int i = 0; i < listOfColumnData.size(); i++) {

						List<OutputData> list = listOfColumnData.get(i);
						row.add(list.get(rowCount));
					}

					this.data.add(row);

				}
			}
			setItems(this.data);

		} catch (Exception e) {
			System.out.println("Error on Building Data For Output Table");
		}

	}

}