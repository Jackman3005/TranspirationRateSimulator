import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import plot.SimulationParameter;
import plot.WaterLossGraphModel;
import plot.WaterLossGraphModelObserverInterface;
import algorithm.ParameterPackage;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

public class InputTable extends ScrollPane {

	private final WaterLossGraphModel model;
	private final GridPane gridPane;

	private final class InputTableModelObserver implements
			WaterLossGraphModelObserverInterface {
		@Override
		public void graphModelHasChanged() {
			buildTable();
		}
	}

	private final class RemoveLineButton extends Button {

		public RemoveLineButton(final ParameterPackage parameterPackage) {
			setFont(new Font(getFont().getName(), 16));
			setText("-");
			setPadding(new Insets(0, 5, 0, 5));

			setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					List<ParameterPackage> parameterPackages = InputTable.this.model
							.getParameterPackages();
					if (parameterPackages.size() > 1) {
						parameterPackages.remove(parameterPackage);
						InputTable.this.model
								.setParameterPackages(parameterPackages);
						buildTable();
					}
				}
			});
		}
	}

	private final class NumbersOnlyTextField extends TextField {

		String REGEX_THAT_MATCHES_NUMBERS_ONLY = "[0-9]*\\.?[0-9]*";

		public NumbersOnlyTextField(final ParameterPackage parameterPackage,
				final SimulationParameter simulationParameter) {

			setText(parameterPackage.getParameterValue(simulationParameter)
					+ "");

			focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(
						ObservableValue<? extends Boolean> observable,
						Boolean oldValue, Boolean currentlyFocused) {
					if (!currentlyFocused) {
						try {
							parameterPackage.setParameterValue(
									simulationParameter,
									Double.parseDouble(getText()));
						} catch (NumberFormatException e) {
						}
					}
				}
			});
		}

		@Override
		public void replaceText(int start, int end, String text) {
			// This ensures the user can only enter numbers in the
			// entry fields

			if (text.matches(this.REGEX_THAT_MATCHES_NUMBERS_ONLY)) {
				super.replaceText(start, end, text);
			}
		}

		@Override
		public void replaceSelection(String text) {
			if (text.matches(this.REGEX_THAT_MATCHES_NUMBERS_ONLY)) {
				super.replaceSelection(text);
			}
		}
	}

	public InputTable(WaterLossGraphModel model) {
		this.model = model;
		setStyle("-fx-background-color:transparent;");
		this.gridPane = new GridPane();
		setMaxHeight(245);
		setContent(this.gridPane);
		setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		model.addObserver(new InputTableModelObserver());
		buildTable();
	}

	private void buildTable() {
		this.gridPane.getChildren().clear();
		List<ParameterPackage> parameterPackages = this.model
				.getParameterPackages();
		int numberOfLines = parameterPackages.size();
		SimulationParameter[] parameters = SimulationParameter.values();
		ArrayList<List<TextField>> inputTextFields = new ArrayList<List<TextField>>();

		float longestParameterFieldWidth = 0;

		ArrayList<Button> listOfRemoveLineButtons = new ArrayList<Button>();
		ArrayList<TextField> parameterTextFields = new ArrayList<TextField>();
		for (int i = 0; i < parameters.length; i++) {
			TextField parameterField = new TextField();
			parameterField.setBorder(Border.EMPTY);

			String nameOfParameter = parameters[i].toString();
			parameterField.setText(nameOfParameter);
			parameterField.setEditable(false);
			FontMetrics fontMetrics = Toolkit.getToolkit().getFontLoader()
					.getFontMetrics(parameterField.getFont());
			float stringWidth = fontMetrics.computeStringWidth(nameOfParameter);
			if ((stringWidth) > longestParameterFieldWidth) {
				longestParameterFieldWidth = stringWidth;
			}
			if (parameters[i].equals(this.model.getIndependentVariable())) {
				parameterField.setDisable(true);
			}

			ArrayList<TextField> rowOfInputFields = new ArrayList<TextField>();
			inputTextFields.add(rowOfInputFields);
			for (int j = 0; j < numberOfLines; j++) {
				ParameterPackage parameterPackageMatchingThisColumn = parameterPackages
						.get(j);
				TextField inputTextField = new NumbersOnlyTextField(
						parameterPackageMatchingThisColumn, parameters[i]);
				listOfRemoveLineButtons.add(new RemoveLineButton(
						parameterPackageMatchingThisColumn));
				if (parameters[i].equals(this.model.getIndependentVariable())) {
					inputTextField.setDisable(true);
					inputTextField.setText("");
				}
				rowOfInputFields.add(inputTextField);
			}

			parameterTextFields.add(parameterField);
		}

		Label parameterLabel = new Label("Parameters");
		parameterLabel
				.setFont(new Font(parameterLabel.getFont().getName(), 20));
		int headerColumnCount = 0;
		this.gridPane.add(parameterLabel, headerColumnCount, 0);

		for (headerColumnCount = 1; headerColumnCount <= numberOfLines; headerColumnCount++) {
			Label lineLabel = new Label("Line " + headerColumnCount);

			lineLabel.setFont(new Font(lineLabel.getFont().getName(), 20));
			Button removeLineButton = listOfRemoveLineButtons
					.get(headerColumnCount - 1);

			AnchorPane paneForLabelAndRemoveLineButton = new AnchorPane();
			paneForLabelAndRemoveLineButton.getChildren().addAll(lineLabel,
					removeLineButton);
			AnchorPane.setLeftAnchor(lineLabel, 0d);
			AnchorPane.setRightAnchor(removeLineButton, 10d);
			this.gridPane.add(paneForLabelAndRemoveLineButton,
					headerColumnCount, 0);
		}
		addAddLineButton(headerColumnCount);

		for (int i = 0; i < parameterTextFields.size(); i++) {
			TextField parameterTextField = parameterTextFields.get(i);
			parameterTextField.setMinWidth(longestParameterFieldWidth + 20);
			parameterTextField.setMaxWidth(longestParameterFieldWidth + 20);
			this.gridPane.add(parameterTextField, 0, i + 1);

			List<TextField> rowOfInputFields = inputTextFields.get(i);
			for (int j = 0; j < rowOfInputFields.size(); j++) {
				TextField inputTextField = rowOfInputFields.get(j);
				inputTextField.setMinWidth(100);
				inputTextField.setMaxWidth(100);
				this.gridPane.add(inputTextField, 1 + j, i + 1);
			}
		}
	}

	private void addAddLineButton(int headerColumnCount) {
		Button addLineButton = new Button("Add Line");
		addLineButton.setMinWidth(65);

		addLineButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				List<ParameterPackage> parameterPackages = InputTable.this.model
						.getParameterPackages();
				ParameterPackage lastParameterPackageInList = parameterPackages
						.get(parameterPackages.size() - 1);
				ParameterPackage newParameterPackage_AKA_NewLine = new ParameterPackage(
						lastParameterPackageInList);
				parameterPackages.add(newParameterPackage_AKA_NewLine);
				InputTable.this.model.setParameterPackages(parameterPackages);
				buildTable();
			}
		});
		this.gridPane.add(addLineButton, headerColumnCount, 0);
	}
}