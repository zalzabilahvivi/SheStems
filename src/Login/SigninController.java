package Login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SigninController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private static final String CSV_FILE_PATH = "src/Admin/users.csv";

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    @FXML
    public void handleSignIn() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (isValidUser(email, password)) {
            if (email.equals("admin@gmail.com") && password.equals("admin123")) {
                // Jika login sebagai admin, redirect ke halaman UserStatistic.fxml
                redirectToUserStatisticPage();
            } else {
                // Jika login sebagai pengguna biasa, redirect ke halaman utama
                redirectToHomePage();
            }
        } else {
            // Login gagal, tampilkan pesan kesalahan
            showAlert("Login Failed", "Invalid email or password.");
        }
    }

    private boolean isValidUser(String email, String password) {
        try {
            Map<String, String> users = readUsersFromCSV();

            // Check if the provided email exists and password matches
            if (users.containsKey(email) && users.get(email).equals(password)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void redirectToHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/HomePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load HomePage.");
        }
    }

    private void redirectToUserStatisticPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/UserStatistic.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load UserStatisticPage.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Map<String, String> readUsersFromCSV() throws IOException {
        Map<String, String> users = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length >= 2) {
                String email = fields[2].trim(); // assuming email is in the third column
                String password = fields[4].trim(); // assuming password is in the fifth column
                users.put(email, password);
            }
        }

        reader.close();
        return users;
    }
}
