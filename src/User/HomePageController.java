package User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import User.components.ArticleCard;
import User.components.ArticleDetailController;
import Admin.components.ScheduleCard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Button btForum;

    @FXML
    private Button btHomePage;

    @FXML
    private Button btMentoring;

    @FXML
    private Button btSetting;

    @FXML
    private HBox scrollableContainer;

    @FXML
    private HBox articleContainer;

    @FXML
    private ScrollPane articleScrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load mentoring schedule into the scrollable container
        ScheduleCard.loadMentoringSchedule(scrollableContainer);

        // Load articles from CSV
        loadArticles();
    }

    @FXML
    private void handleForumButtonClick() {
        // Define action for Forum button click
        System.out.println("Forum button clicked!");
    }

    @FXML
    private void handleHomePageButtonClick() {
        // Define action for Home Page button click
        System.out.println("Home Page button clicked!");
    }

    @FXML
    private void handleSettingButtonClick() {
        // Define action for Setting button click
        System.out.println("Setting button clicked!");
    }

    @FXML
    private void handleOpenSetting() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/Setting.fxml"));
            Parent settingPageParent = loader.load();

            // Create a new stage
            Stage newStage = new Stage();
            newStage.setTitle("Settings"); // Set the title of the new window

            // Create a new scene
            Scene newScene = new Scene(settingPageParent);

            // Set the scene for the stage
            newStage.setScene(newScene);

            // Show the new stage
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Setting.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void handleOpenMentoringUser(ActionEvent event) {
        try {
            Parent mentoringPage = FXMLLoader.load(getClass().getResource("Mentoring.fxml"));
            Node source = (Node) event.getSource();
            AnchorPane root = (AnchorPane) source.getScene().getRoot();
            root.getChildren().setAll(mentoringPage);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading Mentoring.fxml: " + e.getMessage());
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

    private void loadArticles() {
        String csvFile = "src/User/articles.csv";
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                if (data[0].equals("id")) continue; // Skip header

                int id = Integer.parseInt(data[0].trim());
                String title = data[1].trim();
                String content = data[2].trim();
                String image = data[3].trim();
                LocalDate publishDate = LocalDate.parse(data[4].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                ArticleCard articleCard = new ArticleCard(id, title, content, image, publishDate);
                articleCard.setOnOpenDetail(() -> navigateToArticleDetail(id, title, content, image, publishDate));
                articleContainer.getChildren().add(articleCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigateToArticleDetail(int id, String title, String content, String imagePath, LocalDate publishDate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/ArticleDetail.fxml"));
            Parent articleDetailRoot = loader.load();

            // Create a new stage
            Stage newStage = new Stage();
            newStage.setTitle(title); // Set the title of the new window

            // Set minimum width (720p width)
            newStage.setMinWidth(800); // 1280px width for 720p resolution

            // Create a new scene
            Scene newScene = new Scene(articleDetailRoot);

            // Set the scene for the stage
            newStage.setScene(newScene);

            // Show the new stage
            newStage.show();

            // Pass data to the controller of the new scene
            ArticleDetailController controller = loader.getController();
            controller.initialize(title, content, imagePath, publishDate);
            controller.setBackButtonAction(() -> {
                newStage.close(); // Close the current stage when back button is clicked
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading ArticleDetail.fxml: " + e.getMessage());
        }
    }
}
