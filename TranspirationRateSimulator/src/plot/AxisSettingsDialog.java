package plot;

import java.text.DecimalFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AxisSettingsDialog extends Stage {
	private final class SaveAndCloseWhenEnterPressedEventHandler implements
			EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent ke) {
			if (ke.getCode().equals(KeyCode.ENTER)) {
				saveSettingsAndCloseDialog();
			}
		}
	}

	private final class UpdateComponentsEventHandler implements
			ChangeListener<Boolean> {
		@Override
		public void changed(ObservableValue<? extends Boolean> observable,
				Boolean oldValue, Boolean newValue) {
			if (newValue != null && !newValue) {
				updateComponentsWithNewRange();
			}
		}
	}

	private final class OKButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			saveSettingsAndCloseDialog();
		}

	}

	private final class TickMarkTextFieldEventHandler implements
			ChangeListener<Boolean> {
		private final TextField tickMarkTextField;
		private final Slider tickMarkSizeSlider;

		public TickMarkTextFieldEventHandler(TextField tickMarkTextField,
				Slider tickMarkSizeSlider) {
			this.tickMarkTextField = tickMarkTextField;
			this.tickMarkSizeSlider = tickMarkSizeSlider;
		}

		@Override
		public void changed(ObservableValue<? extends Boolean> observable,
				Boolean oldValue, Boolean newValue) {
			if (newValue != null && !newValue) {
				try {
					double parsedDouble = Double
							.parseDouble(this.tickMarkTextField.getText()
									.trim());
					if (parsedDouble > AxisSettingsDialog.this.tickMarkMinimum
							&& parsedDouble < AxisSettingsDialog.this.tickMarkMaximum) {
						this.tickMarkSizeSlider.setValue(parsedDouble);
					}
				} catch (Exception e) {
					this.tickMarkTextField.setText("");
				}
			}
		}

	}

	private final class TextBoxUpdatingMouseDraggedEventHandler implements
			EventHandler<Event> {
		private final TextField tickMarkTextField;
		private final Slider tickMarkSizeSlider;

		public TextBoxUpdatingMouseDraggedEventHandler(
				Slider tickMarkSizeSlider, TextField tickMarkTextField) {
			this.tickMarkSizeSlider = tickMarkSizeSlider;
			this.tickMarkTextField = tickMarkTextField;
		}

		@Override
		public void handle(Event event) {
			double currentValue = this.tickMarkSizeSlider.getValue();

			DecimalFormat numberFormat = new DecimalFormat();
			String text = numberFormat.format(currentValue);

			this.tickMarkTextField.setText(text);
		}
	}

	private final WaterLossGraphModel graphModel;
	private double tickMarkMinimum;
	private double tickMarkMaximum;
	private TextField tickMarkTextField;
	private TextField minTextField;
	private TextField maxTextField;
	private Slider tickMarkSizeSlider;

	public AxisSettingsDialog(WaterLossGraphModel graphModel) {
		this.graphModel = graphModel;
		setTitle("Edit Axis Settings");
		initStyle(StageStyle.UTILITY);
		double xAxisRange = (this.graphModel.getXAxisRangeMaximum() - this.graphModel
				.getXAxisRangeMinimum());
		this.tickMarkMaximum = (xAxisRange / 3);
		this.tickMarkMinimum = (xAxisRange / 50);

		buildDialogAndSetScene(graphModel);
		updateComponentsWithNewRange();
	}

	private void buildDialogAndSetScene(WaterLossGraphModel graphModel) {
		GridPane gridPane = new GridPane();
		Label header = new Label("Independent Parameter Axis Settings");
		String genericFontName = header.getFont().getName();
		header.setFont(new Font(genericFontName, 20));

		GridPane rangePane = buildRangePane();
		GridPane tickMarkPane = buildTickMarkPane(graphModel);
		GridPane okAndCancelButtonPane = builderOkAndCancelPane();

		gridPane.setPadding(new Insets(20));

		gridPane.add(header, 0, 0);
		gridPane.add(rangePane, 0, 1);
		gridPane.add(tickMarkPane, 0, 2);
		gridPane.add(okAndCancelButtonPane, 0, 3);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane);

		setScene(scene);
	}

	private GridPane builderOkAndCancelPane() {
		GridPane okAndCancelButtonPane = new GridPane();
		okAndCancelButtonPane.setAlignment(Pos.CENTER_RIGHT);
		okAndCancelButtonPane.setHgap(30);
		okAndCancelButtonPane.setPadding(new Insets(20, 40, 20, 40));
		Button okButton = new Button("OK");
		Button cancelButton = new Button("Cancel");

		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				close();
			}
		});

		okButton.setOnAction(new OKButtonHandler());

		okAndCancelButtonPane.add(okButton, 0, 0);
		okAndCancelButtonPane.add(cancelButton, 1, 0);

		return okAndCancelButtonPane;
	}

	private GridPane buildTickMarkPane(WaterLossGraphModel graphModel) {
		GridPane tickMarkPane = new GridPane();
		tickMarkPane.setPadding(new Insets(10));

		Label tickMarkIncrementLabel = new Label("TickMark Increment: ");
		tickMarkIncrementLabel.setMinWidth(120);

		this.tickMarkSizeSlider = new Slider();
		this.tickMarkTextField = new TextField(graphModel.getTickUnitSize()
				+ "");

		this.tickMarkSizeSlider.setValue(graphModel.getTickUnitSize());

		this.tickMarkTextField.focusedProperty().addListener(
				new TickMarkTextFieldEventHandler(this.tickMarkTextField,
						this.tickMarkSizeSlider));
		this.tickMarkTextField
				.setOnKeyPressed(new SaveAndCloseWhenEnterPressedEventHandler());

		this.tickMarkSizeSlider.setPadding(new Insets(0, 0, 0, 10));

		this.tickMarkSizeSlider
				.setOnMouseDragged(new TextBoxUpdatingMouseDraggedEventHandler(
						this.tickMarkSizeSlider, this.tickMarkTextField));

		this.tickMarkSizeSlider.setShowTickLabels(true);
		this.tickMarkSizeSlider.setShowTickMarks(true);

		tickMarkPane.add(tickMarkIncrementLabel, 0, 0);
		tickMarkPane.add(this.tickMarkTextField, 1, 0);
		tickMarkPane.add(this.tickMarkSizeSlider, 2, 0);
		return tickMarkPane;
	}

	private GridPane buildRangePane() {
		GridPane rangePane = new GridPane();
		rangePane.setPadding(new Insets(10));
		rangePane.setAlignment(Pos.CENTER_LEFT);

		rangePane.setHgap(10);

		Label minLabel = new Label("Min");
		Label maxLabel = new Label("Max");

		Label xAxisRangeLabel = new Label("X-Axis Range: ");

		this.minTextField = new TextField(
				this.graphModel.getXAxisRangeMinimum() + "");
		this.maxTextField = new TextField(
				this.graphModel.getXAxisRangeMaximum() + "");

		this.maxTextField
				.setOnKeyPressed(new SaveAndCloseWhenEnterPressedEventHandler());
		this.minTextField
				.setOnKeyPressed(new SaveAndCloseWhenEnterPressedEventHandler());

		this.minTextField.focusedProperty().addListener(
				new UpdateComponentsEventHandler());
		this.maxTextField.focusedProperty().addListener(
				new UpdateComponentsEventHandler());

		this.minTextField.setMaxWidth(100);
		this.maxTextField.setMaxWidth(100);

		rangePane.add(minLabel, 1, 0);
		rangePane.add(maxLabel, 2, 0);
		rangePane.add(xAxisRangeLabel, 0, 1);
		rangePane.add(this.minTextField, 1, 1);
		rangePane.add(this.maxTextField, 2, 1);
		return rangePane;
	}

	private void updateComponentsWithNewRange() {
		try {
			double rangeMinimum = Double.parseDouble(this.minTextField
					.getText().trim());
			double rangeMaximum = Double.parseDouble(this.maxTextField
					.getText().trim());
			if (rangeMaximum < rangeMinimum) {
				double temp = rangeMaximum;
				rangeMaximum = rangeMinimum;
				rangeMinimum = temp;
				this.maxTextField.setText(rangeMaximum + "");
				this.minTextField.setText(rangeMinimum + "");
			}

			double range = rangeMaximum - rangeMinimum;

			this.tickMarkMaximum = (range / 3);
			this.tickMarkMinimum = (range / 50);

			double valueToSetForSlider = this.tickMarkSizeSlider.getValue();
			if (valueToSetForSlider > this.tickMarkMaximum
					|| valueToSetForSlider < this.tickMarkMinimum) {
				valueToSetForSlider = range / 6;
			}

			this.tickMarkSizeSlider.setMax(this.tickMarkMaximum);
			this.tickMarkSizeSlider.setMin(Math.min(
					Math.max(0.1, this.tickMarkMinimum), 1));
			this.tickMarkSizeSlider
					.setMajorTickUnit((this.tickMarkMaximum - this.tickMarkMinimum) / 4);
			this.tickMarkSizeSlider.setValue(valueToSetForSlider);
			DecimalFormat numberFormat = new DecimalFormat();
			String text = numberFormat.format(valueToSetForSlider);
			this.tickMarkTextField.setText(text);

		} catch (NumberFormatException e) {
		}
	}

	private void saveSettingsAndCloseDialog() {
		try {
			double rangeMin = Double
					.parseDouble(AxisSettingsDialog.this.minTextField.getText());
			double rangeMax = Double
					.parseDouble(AxisSettingsDialog.this.maxTextField.getText());
			double tickMarkIncrement = Double
					.parseDouble(AxisSettingsDialog.this.tickMarkTextField
							.getText());

			AxisSettingsDialog.this.graphModel.setXAxisRangeAndTickUnit(
					rangeMin, rangeMax, tickMarkIncrement);
			close();

		} catch (NumberFormatException e) {
		}
	}
}
