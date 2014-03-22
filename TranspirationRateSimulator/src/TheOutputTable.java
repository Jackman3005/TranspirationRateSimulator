

/**
 * @author: Zhaofei
 * @version: 3/1/14
 */

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

public class TheOutputTable extends TableView
{
	 
    public TheOutputTable() {
    	
    	 final Label label = new Label("The Output Table");
         label.setFont(new Font("Arial", 20));
  
         TableColumn firstNameCol = new TableColumn("Element");
         TableColumn lastNameCol = new TableColumn("WaterLoss");
      
           
         this.getColumns().addAll(firstNameCol, lastNameCol);
        
    }

	}
         