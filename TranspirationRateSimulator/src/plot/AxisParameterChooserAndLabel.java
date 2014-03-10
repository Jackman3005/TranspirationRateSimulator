package plot;

import java.util.Arrays;

import javafx.scene.control.ComboBox;

import com.sun.javafx.collections.ObservableListWrapper;

public class AxisParameterChooserAndLabel extends ComboBox<SimulationParameter> {

	public AxisParameterChooserAndLabel() {
		ObservableListWrapper<SimulationParameter> parameters = new ObservableListWrapper<SimulationParameter>(
				Arrays.asList(SimulationParameter.values()));
		this.setItems(parameters);
		this.getSelectionModel().selectFirst();
		

	}

}
