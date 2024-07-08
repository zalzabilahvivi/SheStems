package User.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.time.LocalDate;

public class ArticleCardController {

    @FXML
    private VBox articleCard;

    @FXML
    private ImageView articleImage;

    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button readMoreButton;

    private int id;
    private String title;
    private String content;
    private String imagePath;
    private LocalDate publishDate;
    private Runnable onOpenDetailCallback;

    public void initialize(int id, String title, String content, String imagePath, LocalDate publishDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.publishDate = publishDate;

        // Set data to UI elements
        titleLabel.setText(title);
        dateLabel.setText("Published: " + publishDate.toString());

        // Load image if imagePath is provided
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
                articleImage.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception (e.g., show default image or log error)
            }
        }

        // Set action for Read More button
        readMoreButton.setOnAction(event -> {
            if (onOpenDetailCallback != null) {
                onOpenDetailCallback.run();
            }
        });
    }

    public void setOnOpenDetail(Runnable callback) {
        this.onOpenDetailCallback = callback;
    }
}
