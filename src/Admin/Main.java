package Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the Signin.fxml file
        Parent root = FXMLLoader.load(getClass().getResource("/Login/Signin.fxml"));
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(new Scene(root, 1440, 1024));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
