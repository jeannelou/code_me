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
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondWindowController implements Initializable{

    @FXML
    private Button nextButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button lastButton;

    @FXML
    private Button previousButton;

    @FXML
    private TextField nameField;
    
    @FXML
    private Label nameValidationMessage;
    
    @FXML
    private TextField telephoneField;
    
    @FXML
    private Label telephoneValidationMessage;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private Label emailValidationMessage;

    @FXML
    private Button addButton;    

    @FXML
    private Button firstButton;
    
    //This method is called when Adding or Editing a Contact
    //This is where the index is sent as a parameter
    //-1 when adding a contact and corresponding index of the array list when editing
    public void setIndex(int index) {
    	this.index = index;
    	//Display Contact if index is greater than or equal to zero
    	if(index >= 0) { displayRecord(); }
    }
    
    //Override initialize method of Initializable Interface
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			//Set Database Directory
			path = "c:\\temp\\phonebookDatabase.txt";
			
			//Load Contacts from Database File
			loadFromFile();
			
			//Hide validation message objects (Name, Telephone, Email Validation Error Message
			hideValidationMessage();
			 
			//Initialize Name Text Field Event Listener
			nameField.textProperty().addListener((observable, oldValue, newValue) -> {
				//Hide validation message objects (Name, Telephone, Email Validation Error Message
				hideValidationMessage(); 
			});
			
			//Initialize Telephone Text Field Event Listener
			telephoneField.textProperty().addListener((observable, oldValue, newValue) -> {
				//Hide validation message objects (Name, Telephone, Email Validation Error Message
				hideValidationMessage(); 
			});
			
			//Initialize Email Text Field Event Listener
			emailField.textProperty().addListener((observable, oldValue, newValue) -> {
				//Hide validation message objects (Name, Telephone, Email Validation Error Message
				hideValidationMessage(); 
			});
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//Method that hides Validation Error Message
	//Did not set the Label's Visible to False because it cannot be selected on scene builder
	private void hideValidationMessage() {
		nameValidationMessage.setVisible(false);
		telephoneValidationMessage.setVisible(false);
		emailValidationMessage.setVisible(false);
	}
	
	//Clears the Name, Telephone and Email Text Field when adding new Contact
	private void clearTextFields() {
		nameField.setText("");
		telephoneField.setText("");
		emailField.setText("");
	}
	
	//Class Variables
    ArrayList<Person> pplList = new ArrayList<Person>();
	String path;
	int index;
	
	
		public void saveToFile() {
			File f = new File(path);
			try ( PrintWriter pw = new PrintWriter(f) )  {
				for(Person p : pplList) {
					pw.printf("%s;%s;%s\n", p.getName(), p.getTelephone(), p.getEmail());
				}
			} catch (FileNotFoundException e) {
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
				String record = records.nextLine();
				Scanner fields = new Scanner(record);
				fields.useDelimiter(";\\s*");
				
				String name = fields.next();
				String telephone = fields.next();
				String email = fields.next();
						
				Person person = new Person(name,telephone, email);
				pplList.add(person);
				fields.close();
			}
			records.close();
		}
	    
		public void displayRecord() {
			//Displays record if Array List is not empty and index is not less than zero (Adding New Contact)
			if(pplList.size() > 0 && index >=0) {
				nameField.setText(pplList.get(index).getName());
				telephoneField.setText(""+pplList.get(index).getTelephone());
				emailField.setText(pplList.get(index).getEmail());
			}
		}
		
		//Public Add method that calls the private Add method
		public void addRecord() {
			this.add(new ActionEvent());
		}
		
		//Loads the HomePage Scene
		@FXML
		private void showHomePage() throws IOException{
		    	
			Main.showHomePagePhonebook();
		
		}
		
	//Private Add method
	@FXML
	void add(ActionEvent event) {
		//Checks if Name, Telepphone and Email Text Fields are not empty before Adding
		if(nameField.getText().trim().length() > 0 || telephoneField.getText().trim().length() > 0 || emailField.getText().trim().length() > 0) 
		{
			//Confirmation Popup screen (Yes/No)
			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonNo = new ButtonType("No");
			Alert alert = new Alert(AlertType.WARNING,
			        "Field(s) are not empty.\n" 
			        + "Are you sure you want to add a new contact?",
			        buttonYes,
			        buttonNo);
			
			//Sets the alert title
			alert.setTitle("Add New Record Confirmation");
			Optional<ButtonType> result = alert.showAndWait();
				
			//Checks if user 
			if (result.orElse(buttonNo) == buttonYes) {
				clearTextFields();
			}
			//Else Do nothing
		} 
		else
		{
			//Calls clear Text Fields method
			clearTextFields();
		}
			
	}
	
    @FXML
    void save(ActionEvent event) {
    	
    	Boolean personExist = false;
    	String name = nameField.getText();
		String telephone = telephoneField.getText();
		String email = emailField.getText();
		
		//Hides existing validation error message if there's any
		hideValidationMessage();
		
    	//Validates if Text Fiels are not empty (Name, Telephone and Email)
		if(nameField.getText().trim().length() == 0) {
			nameValidationMessage.setText("* Name should not be empty");
    		nameValidationMessage.setVisible(true);
		}else if(telephoneField.getText().trim().length() == 0) {
			telephoneValidationMessage.setText("* Telephone should not be empty");
			telephoneValidationMessage.setVisible(true);
		}else if(emailField.getText().trim().length() == 0) {
			emailValidationMessage.setText("* Email should not be empty");
			emailValidationMessage.setVisible(true);
		}else {
			//Validate Email Address
			if(!isValidEmail(email)) {
				emailValidationMessage.setText("* Invalid email address format");
				emailValidationMessage.setVisible(true);
			}
			//Validate Phone Number
			else if(!validatePhoneNumber(telephone)){ 
				telephoneValidationMessage.setText("* Invalid telephone number format");
				telephoneValidationMessage.setVisible(true);
			} else {
				if(index < 0) {
					//Check if the person exist in the database or in the array list
					for(Person person: pplList) {
			    		if(person.getName().trim().toUpperCase().equals(name.trim().toUpperCase())) {
			    			personExist = true;
			    		}
			    	}
					//If Contact does not exist
					if(!personExist) {
						//Create the person object
			    		Person person = new Person(name, telephone,email);
			    		//Add the newly created contact to contact array list
						pplList.add(person);
						//Save the update Contact array list to database file
						saveToFile();
						
						//Prompt Sucess saving message box
						Alert a = new Alert(AlertType.INFORMATION);
						a.setTitle("Status");
						a.setContentText("Successfully saved contact");
						a.showAndWait();
						
						//points the index to the newly created contact
						index =  pplList.size() - 1;
						//calls the display record method
						displayRecord();
			    	} else {
			    		//throws a validation errr message if contactx already exist
			    		nameValidationMessage.setText("* Contact already exist");
			    		nameValidationMessage.setVisible(true);
			    	}
			    		
				} else {
					//Updates the information of the existing contact
					Person person = pplList.get(index);
					person.setName(nameField.getText().trim());
					person.setTelephone(telephoneField.getText().trim());
					person.setEmail(emailField.getText().trim());
					
					//Update the database file with the updated contact information
					saveToFile();
					
					//Shows Success Updaing message box
					Alert a = new Alert(AlertType.INFORMATION);
					a.setTitle("Status");
					a.setContentText("Successfully updated contact");
					a.showAndWait();
				}
			}
			
		}
		
    }
    
    //Validate Email Method
    private boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    
    //Validate Telephone Method
    private boolean validatePhoneNumber(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}")) return true;
		//validating phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		//validating phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		//validating phone number where area code is in braces ()
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		//return false if nothing matches the input
		else return false;
	}
    
    @FXML
    void delete(ActionEvent event) {
    	
    	Person person = pplList.get(index);
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");
		Alert alert = new Alert(AlertType.WARNING,
		        "Are you sure you want to delete (" + person.getName() + ") ?",
		        buttonYes,
		        buttonNo);

		alert.setTitle("Record Deletion Confirmation");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.orElse(buttonNo) == buttonYes) {
			pplList.remove(index);
			if(index > 0) {
				index--;
				displayRecord();
			}
			else if (index < pplList.size()) {
				index++;
				displayRecord();
			}
			else {
				nameField.setText("");
				telephoneField.setText("");
				emailField.setText("");
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Navigation");
				a.setContentText("No more records remaining");
				a.showAndWait();
			}
			saveToFile();
		}
    	
    }

    @FXML
    void first(ActionEvent event) {
    	index = 0;
	    displayRecord();
    }

    @FXML
    void previous(ActionEvent event) {
    	index--;
		if(index >= 0) 
			displayRecord();
		else {
			index++;
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Navigation");
			a.setContentText("First Record");
			a.showAndWait();
		}

    }

    @FXML
    void next(ActionEvent event) {
    	
    	index++;
		if(index < pplList.size()) 
			displayRecord();
		else {
			index--;
		Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Navigation");
			a.setContentText("Last Record");
			a.showAndWait();

   }

    }

    @FXML
    void last(ActionEvent event) {
    	index = pplList.size() - 1;
		displayRecord();


    }
    
   

}














