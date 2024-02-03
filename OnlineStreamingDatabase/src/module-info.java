module OnlineStreamingDatabase {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires mongo.java.driver;
	
	opens application to javafx.graphics, javafx.fxml;
}
