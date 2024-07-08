package User.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.InputStream;
import java.time.LocalDate;

public class ArticleDetailController {

    @FXML
    private ImageView articleImageView;

    @FXML
    private Label titleLabel;

    @FXML
    private Text contentText;

    @FXML
    private Label dateLabel;

    @FXML
    private Button backButton;

    @FXML
    private VBox articleDetailPane;

    private Runnable backButtonAction;

    public void initialize(String title, String content, String imagePath, LocalDate publishDate) {
        titleLabel.setText(title);
        contentText.setText(content);
        dateLabel.setText("Published: " + publishDate.toString());
    
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                InputStream stream = getClass().getResourceAsStream(imagePath);
                if (stream == null) {
                    throw new NullPointerException("Image not found: " + imagePath);
                }
                Image image = new Image(stream);
                if (image.isError()) {
                    throw new RuntimeException("Failed to load image: " + imagePath);
                }
                articleImageView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle image loading error
            }
        }
    
        backButton.setOnAction(event -> {
            if (backButtonAction != null) {
                backButtonAction.run();
            }
        });
    }    

    public void setBackButtonAction(Runnable action) {
        this.backButtonAction = action;
    }

    @FXML
    private void handleBackButtonClick() {
        // Define what happens when the back button is clicked
        System.out.println("Back button clicked!");
        if (backButtonAction != null) {
            backButtonAction.run();
        }
    }
}
