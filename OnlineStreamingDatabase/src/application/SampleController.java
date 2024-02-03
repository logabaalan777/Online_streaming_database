package application;

import javafx.event.ActionEvent;   
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;

public class SampleController{
	
    @FXML
    private Button homebutton;
    
    @FXML
    private Button accountbutton;

    @FXML
    private Button homebutton1;

    @FXML
    private Button listbutton;

    @FXML
    private Button moviesbutton;

    @FXML
    private Button seriesbutton;

    @FXML
    private Button watchleo;
    

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button btn;

    @FXML
    private void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Authenticate user against MongoDB (replace with your MongoDB connection details)
        if (authenticateUser(username, password)) {
            
            try {
                Main.navigateDashboard();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }
    
    @FXML
    void toSeries(MouseEvent event) {
        navigateseries();
    }

    @FXML
    void toMovies(ActionEvent event) {
        navigateMovies();
    }
    
    @FXML
    void toAccount(ActionEvent event) {
    	navigateAccount();
    }
   
	private void navigateAccount() {
		try {
    		System.out.println();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Accounts.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void navigateseries() {
    	try {
    		System.out.println();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("series.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigateMovies() {
    	try {
    		System.out.println();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("movies.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) btn.getScene().getWindow();

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean authenticateUser(String username, String password) {
        
        String connectionString = "mongodb://localhost:27017";
        String databaseName = "Onlinestream";
        String collectionName = "users";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Query the database to check if the user exists with the given credentials
            Document query = new Document("username", username).append("password", password);
            return collection.find(query).first() != null;
        }
    }
}
