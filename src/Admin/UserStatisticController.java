package Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

public class UserStatisticController {

    @FXML
    private TableView<User> allusertable;

    @FXML
    private TableColumn<User, Integer> idUserColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, LocalDate> joinDateColumn;

    @FXML
    private TableColumn<User, String> statusColumn;

    @FXML
    private TableColumn<User, String> addressColumn;

    @FXML
    private TableColumn<User, String> stemFieldColumn;

    @FXML
    private TextField useractive;

    private final List<User> userList = new ArrayList<>();

    @FXML
    private void initialize() {
        // Initialize TableView columns
        idUserColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        joinDateColumn.setCellValueFactory(cellData -> cellData.getValue().joinDateProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        stemFieldColumn.setCellValueFactory(cellData -> cellData.getValue().stemFieldProperty());

        loadUsersFromCSV();
    }

    public void handleMentoringPage(ActionEvent event) {
        try {
            Parent mentoringPageParent = FXMLLoader.load(getClass().getResource("Mentoring.fxml"));
            Scene mentoringPageScene = new Scene(mentoringPageParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(mentoringPageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsersFromCSV() {
    try {
        File csvFile = new File("src/Admin/users.csv");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));

        // Skip header
        String line = bufferedReader.readLine();

        // Read each line
        while ((line = bufferedReader.readLine()) != null) {
            String[] fields = line.split(",");

            if (fields.length >= 5) {
                int id = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                String email = fields[2].trim();
                
                // Handling potential date parsing error
                LocalDate joinDate;
                try {
                    joinDate = LocalDate.parse(fields[3].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (DateTimeParseException e) {
                    // Handle the error, maybe log it or set a default date
                    joinDate = LocalDate.now(); // Default to current date
                }
                
                String status = fields[4].trim();

                // Additional fields
                String address = ""; // Assuming the 6th column is the address
                String stemField = ""; // Assuming the 7th column is the STEM field

                if (fields.length > 5) {
                    address = fields[5].trim();
                }

                if (fields.length > 6) {
                    stemField = fields[6].trim();
                }

                User user = new User(id, name, email, joinDate, status, address, stemField);
                userList.add(user);
            }
        }

        bufferedReader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Add data to TableView
    allusertable.getItems().addAll(userList);

    // Calculate and set the number of active users
    long activeUsersCount = userList.stream()
            .filter(user -> "Active".equalsIgnoreCase(user.getStatus()))
            .count();
    useractive.setText(String.valueOf(activeUsersCount));
}
}
