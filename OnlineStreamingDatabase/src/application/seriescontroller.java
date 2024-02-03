package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class seriescontroller {

    @FXML
    private Button homesbtn;
    
    @FXML
    private Button btn;
    
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
}
