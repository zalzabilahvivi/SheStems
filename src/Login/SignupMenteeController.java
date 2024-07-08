package Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SignupMenteeController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> stemFieldComboBox;

    @FXML
    private void initialize() {
        // Inisialisasi pilihan STEM fields
        stemFieldComboBox.getItems().addAll("Science", "Technology", "Engineering", "Mathematics");
    }

    @FXML
    private void handleSignupButtonAction(ActionEvent event) {
        String id = UUID.randomUUID().toString();
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String password = passwordField.getText();
        String stemField = stemFieldComboBox.getValue();
        String joinDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String status = "Active";

        // Validasi data input (opsional)

        // Simpan data ke CSV
        List<String> menteeData = Arrays.asList(id, fullName, email, address, password, joinDate, stemField, status);
        File csvFile = new File("src/Admin/users.csv");

        try {
            FileUtils.writeLines(csvFile, StandardCharsets.UTF_8.name(), Arrays.asList(String.join(",", menteeData)), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Bersihkan field setelah signup
        fullNameField.clear();
        emailField.clear();
        addressField.clear();
        passwordField.clear();
        stemFieldComboBox.getSelectionModel().clearSelection();
    }
}
