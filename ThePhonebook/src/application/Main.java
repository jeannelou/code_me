package application;
	

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application{

	private Stage primaryStage;
    public static AnchorPane page;
    
    @Override
    public void start(Stage primaryStage)throws IOException{
    	
    	this.primaryStage = primaryStage;
    	this.primaryStage.setTitle("Phonebook");
    	
    	showHomepagePhonebook();
    	
    }
    
    private void showHomepagePhonebook() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("HomepagePhonebook.fxml"));
    	page = loader.load();
    	Scene scene = new Scene(page);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }	
    
    public static void showHomePagePhonebook() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("HomepagePhonebook.fxml"));
    	AnchorPane page1 = loader.load();
    	page.getChildren().setAll(page1);
    	
    }
            
    public static void showNewPage(int index) throws IOException {
    	try {
    		FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(Main.class.getResource("SecondWindow.fxml"));
        	AnchorPane page2 = loader.load();
        	SecondWindowController secondpage = loader.<SecondWindowController>getController();
        	secondpage.setIndex(index);
        	page.getChildren().setAll(page2);
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
  
    
    public static void main(String[] args) {
        launch(args);
    }

}
