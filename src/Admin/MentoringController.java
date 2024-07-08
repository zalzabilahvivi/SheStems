package Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Admin.components.MentorCard;
import Admin.components.ScheduleCard;

public class MentoringController implements Initializable {

    @FXML
    private HBox scrollableContainer;

    @FXML
    private Button logoutButton;

    @FXML
    private HBox scrollableContainerMentor;

    @FXML
    private TableView<Mentor> mentorTable;
    @FXML
    private TableColumn<Mentor, String> nameColumn;
    @FXML
    private TableColumn<Mentor, String> professionColumn;
    @FXML
    private TableColumn<Mentor, String> ratingColumn;
    @FXML
    private TableColumn<Mentor, String> bioColumn;

    private ObservableList<Mentor> mentorData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load mentoring schedules
        ScheduleCard.loadMentoringSchedule(scrollableContainer);

        // Set up logout button event handler
        logoutButton.setOnAction(this::handleLogout);

        // Initialize the columns in the TableView
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        professionColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        bioColumn.setCellValueFactory(new PropertyValueFactory<>("bio"));

        // Load mentor list from CSV
        loadMentorListFromCSV();
    }

    private void loadMentorListFromCSV() {
        String csvFile = "src/Admin/MentorList.csv"; // Adjust path as per your project structure
        String line;
        String cvsSplitBy = ",";

        boolean isFirstLine = true; // Flag to skip the first line (header)

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the first line
                    continue;
                }
                String[] data = line.split(cvsSplitBy);
                if (data.length == 5) {
                    String imageUrl = data[0].trim();
                    String name = data[1].trim();
                    String profession = data[2].trim();
                    String rating = data[3].trim();
                    String bio = data[4].trim();

                    // Create Mentor instance and add to the list
                    Mentor mentor = new Mentor(imageUrl, name, profession, rating, bio);
                    mentorData.add(mentor);

                    // Create MentorCard instance and add to scrollableContainerMentor
                    MentorCard mentorCard = new MentorCard(imageUrl, name, profession, rating, bio);
                    scrollableContainerMentor.getChildren().add(mentorCard);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set data to the table
        mentorTable.setItems(mentorData);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login/Signin.fxml"));
            AnchorPane root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
