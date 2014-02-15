package plot;

import java.awt.event.MouseMotionListener;
import java.beans.EventHandler;
import java.util.Arrays;

import javafx.event.EventType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;

import com.sun.beans.decoder.ElementHandler;
import com.sun.javafx.collections.ObservableListWrapper;

public class AxisParameterChooserAndLabel extends
		ComboBox<SimulationParameters> {


	public AxisParameterChooserAndLabel() {
		ObservableListWrapper<SimulationParameters> parameters = new ObservableListWrapper<SimulationParameters>(
				Arrays.asList(SimulationParameters.values()));
		this.setItems(parameters);
		this.getSelectionModel().selectFirst();
		this.backgroundProperty().set(Background.EMPTY);
		

	}

}
