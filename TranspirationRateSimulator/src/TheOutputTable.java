
/**
 * @author: Zhaofei
 * @version: 3/1/14
 */

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;

public class TheOutputTable extends TableView {

	public TheOutputTable() {

		final Label label = new Label("The Output Table");
		label.setFont(new Font("Arial", 20));

		TableColumn firstNameCol = new TableColumn("Element");
		TableColumn lastNameCol = new TableColumn("WaterLoss");

		getColumns().addAll(firstNameCol, lastNameCol);

	}

}
