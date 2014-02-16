package plot;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.scene.control.ComboBox;

import java.util.Arrays;

public class AxisParameterChooserAndLabel extends
		ComboBox<SimulationParameters> {


	public AxisParameterChooserAndLabel() {
		ObservableListWrapper<SimulationParameters> parameters = new ObservableListWrapper<SimulationParameters>(
				Arrays.asList(SimulationParameters.values()));
		this.setItems(parameters);
		this.getSelectionModel().selectFirst();
		//this.backgroundProperty().set(Background.EMPTY);
		

	}

}
