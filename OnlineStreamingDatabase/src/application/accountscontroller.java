package application;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import org.bson.Document;

public class accountscontroller {
	
    @FXML
    private Button btn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField mobileTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField subscribeTextField;

    @FXML
    private Button addButton;
    
    @FXML
    private Button hmbtn;

    @FXML
    void toHome(ActionEvent event) {
    	navigateHome();
    }
   
	private void navigateHome() {
		try {
    		System.out.println();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    // MongoDB connection parameters
    private static final String MONGODB_HOST = "localhost";
    private static final int MONGODB_PORT = 27017;
    private static final String DATABASE_NAME = "Onlinestream";
    private static final String COLLECTION_NAME = "details";

    @FXML
    void addButtonClicked(ActionEvent event) {
        String name = nameTextField.getText();
        String mobile = mobileTextField.getText();
        String email = emailTextField.getText();
        String subscribe = subscribeTextField.getText();

        // Create a MongoDB document with the field values
        Document document = new Document("name", name)
                .append("mobile", mobile)
                .append("email", email)
                .append("subscribe", subscribe);

        // Connect to MongoDB and insert the document into the collection
        try (MongoClient mongoClient = new MongoClient(MONGODB_HOST, MONGODB_PORT)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            collection.insertOne(document);

            // Show a pop-up message
            showSuccessAlert("User added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Error adding user.");
        }
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
