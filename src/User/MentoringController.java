package User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load mentoring schedules
        ScheduleCard.loadMentoringSchedule(scrollableContainer);

        // Set up logout button event handler
        logoutButton.setOnAction(this::handleLogout);

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
    
                    // Create MentorCard instance and add to scrollableContainerMentor
                    MentorCard mentorCard = new MentorCard(imageUrl, name, profession, rating, bio);
                    scrollableContainerMentor.getChildren().add(mentorCard);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    private void handleOpenHomePage(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Node source = (Node) event.getSource();
            AnchorPane root = (AnchorPane) source.getScene().getRoot();
            root.getChildren().setAll(homePage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOpenForum(ActionEvent event) {
        try {
            Parent forumPage = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            Node source = (Node) event.getSource();
            AnchorPane root = (AnchorPane) source.getScene().getRoot();
            root.getChildren().setAll(forumPage);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Forum.fxml: " + e.getMessage());
        }
    }
}
