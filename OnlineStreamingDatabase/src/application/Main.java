package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage CStage;
    public static Stage primaryStage;
    public static Stage MoviesStage;
    public static Stage SeriesStage;
    public static Scene moviescene;
    public static Scene seriesscene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        CStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        Scene scene = new Scene(root, 815, 600);

        primaryStage.setTitle("Online Streaming Videos");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialize MoviesStage
        MoviesStage = new Stage();
        // Initialize SeriesStage
        SeriesStage = new Stage();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void navigateDashboard() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("dashboard.fxml"));
        Scene scene = new Scene(root, 918, 683);
        primaryStage.setScene(scene);
        primaryStage.setTitle("www.netflix.com");
    }
}
