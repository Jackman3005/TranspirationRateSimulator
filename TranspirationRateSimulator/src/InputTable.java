
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
@SuppressWarnings("rawtypes")
public class InputTable extends TableView{
 
    private TableView<Parameter> table = new TableView<Parameter>();
    private final ObservableList<Parameter> data =
            FXCollections.observableArrayList(
            new Parameter("Leaf width (cm)", ""),
            new Parameter("Leaf area (cm^2)",""),
            new Parameter("Stoma density (#/mm^2)", ""),
            new Parameter("Radius of stoma (um)", ""),
            new Parameter("Depth of stoma (um)", ""),
            new Parameter("Temperature (C)",""),
            new Parameter("Relative humidity of air (%)", ""),
            new Parameter("Windspeed (mph)", ""));
 
     @SuppressWarnings({ "unchecked" })
	public InputTable (Stage stage1){
    	 
        Scene scene = new Scene(new Group());
 
        final Label label = new Label("Parameter Input Table");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("Parameter");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
            new PropertyValueFactory<Parameter, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
       
        @SuppressWarnings({ })
		TableColumn lastNameCol = new TableColumn("Number");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
            new PropertyValueFactory<Parameter, String>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());


        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol);
 
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("Parameters");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Numbers");
 
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
     
    }
 
    public static class Parameter {
 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;

        private Parameter(String fName, String lName) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String lName) {
            lastName.set(lName);
        }
    }
    
    class EditingCell extends TableCell<Parameter, Double> {
    	 
        private TextField textField;
       
        public EditingCell() {}
       
        @Override
        public void startEdit() {
            super.startEdit();
           
            if (textField == null) {
                createTextField();
            }
           
            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }
       
        @Override
        public void cancelEdit() {
            super.cancelEdit();
           
            setText(String.valueOf(getItem()));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
   
        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);
           
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                }
            }
        }
   
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
               
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(Double.parseDouble(textField.getText()));
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }
       
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}