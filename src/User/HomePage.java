package User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/User/HomePage.fxml"));

            // Set the title of the window
            primaryStage.setTitle("Home Page");

            // Create a scene with the loaded FXML root node
            Scene scene = new Scene(root);

            // Set the scene to the stage
            primaryStage.setScene(scene);

            // Show the stage
            primaryStage.show();
        } catch (Exception e) {
            // Handle any exceptions that occur during FXML loading
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
