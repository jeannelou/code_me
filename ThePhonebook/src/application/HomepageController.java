package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class HomepageController implements Initializable{
	
	@FXML
	private TextField textSearch;
	
	@FXML
	private ComboBox<String> comboSearchBy;
	
	@FXML
	private TableView<Person> phonebookTableView;
	
	ArrayList<Person> pplList = new ArrayList<Person>();
	String path;
	
	@FXML
	private void addContact() throws IOException{
	    
		//Add Record
		//-1 means new record
		//showNewPage is reused for Add and Edit
		Main.showNewPage(-1);
	
	}
	
	@FXML
	private void editContact() throws IOException{
		//Edit Record
		Person person = phonebookTableView.getSelectionModel().getSelectedItem();
		int index = 0;
		for(Person p: pplList) {
			if(p.getName().trim().equals(person.getName().trim())){
				break;
			}else {index++; }
		}
		
		//Index refers to the position of the record in pplList
		Main.showNewPage(index);
	}
	
	@FXML
	private void deleteContact() throws IOException{
		Person person = phonebookTableView.getSelectionModel().getSelectedItem();
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");
		Alert alert = new Alert(AlertType.WARNING,
		        "Are you sure you want to delete (" + person.getName() + ") ?",
		        buttonYes,
		        buttonNo);

		alert.setTitle("Record Deletion Confirmation");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.orElse(buttonNo) == buttonYes) {
			for(Person p: pplList) {
				if(p.getName().trim().equals(person.getName().trim())){
					pplList.remove(person);
					break;
				}
			}
			saveToFile();
			phonebookTableView.getItems().clear();
			phonebookTableView.getItems().addAll(pplList);
		}
		
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			
			//Initialize Search By ComboBox
			ObservableList<String> elements = FXCollections.observableArrayList( "Name", "Telephone", "Email");
			comboSearchBy.setItems(elements);
			comboSearchBy.setValue("Name");
			
			//Create Columns for Table View
			TableColumn nameColumn = new TableColumn("Name");
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			TableColumn telephoneColumn = new TableColumn("Telephone");
			telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
			TableColumn emailColumn = new TableColumn("Email");
			emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
			
			//Set Table View Column
			phonebookTableView.getColumns().addAll(nameColumn, telephoneColumn, emailColumn);
			
			//Set Text Database Directory
			path = "c:\\temp\\phonebookDatabase.txt";
			
			//Load Database to Person Array List
			loadFromFile();
			
			//Set Data Source of Table View which is the Person Array List
			phonebookTableView.getItems().addAll(pplList);
			
			//Declare event listener to Search TextField then call Table View Filtering Critera
			textSearch.textProperty().addListener((observable, oldValue, newValue) -> { 
				searchContact(); 
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadFromFile() throws FileNotFoundException {
		File f = new File(path);
		if(!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		Scanner records = new Scanner(f);
		while(records.hasNextLine()) {
			//Read first Record
			String record = records.nextLine();
			
			//Initialize Scanner
			Scanner fields = new Scanner(record);
			
			//Set Delimiter to be used
			fields.useDelimiter(";\\s*");
			
			//Retrieve and store values to variables
			String name = fields.next();
			String telephone = fields.next();
			String email = fields.next();
					
			//Create Person Object
			Person person = new Person(name,telephone, email);
			
			//Add Person to Array List
			pplList.add(person);
			
			//Close Scanner
			fields.close();
		}
		records.close();
	}
    
	public void saveToFile() {
		File f = new File(path);
		try ( PrintWriter pw = new PrintWriter(f) )  {
			for(Person p : pplList) {
				//Write to File
				pw.printf("%s;%s;%s\n", p.getName(), p.getTelephone(), p.getEmail());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	void searchContact() {
		
		ArrayList<Person> pplListTemp = new ArrayList<Person>();
		
		//Check combobox search
		switch(comboSearchBy.getValue()) {
		case "Name":
			for(Person p: pplList) {
				//Check Person's Name if Name is selected
				if(p.getName().contains(textSearch.getText())) {
					//Add to Temporary Person Array List
					pplListTemp.add(p);
				}
			}
			break;
		case "Telephone":
			for(Person p: pplList) {
				//Check Person's Telephone if Telephone is selected
				if(p.getTelephone().contains(textSearch.getText())) {
					//Add to Temporary Person Array List
					pplListTemp.add(p);
				}
			}
			break;
		case "Email":
			for(Person p: pplList) {
				//Check Person's Email if Email is selected
				if(p.getEmail().contains(textSearch.getText())) {
					//Add to Temporary Person Array List
					pplListTemp.add(p);
				}
			}
			break;
		}
		
		//Clears the Table View
		phonebookTableView.getItems().clear();
		
		//Reload Temporary List
		phonebookTableView.getItems().addAll(pplListTemp);
	}

}


