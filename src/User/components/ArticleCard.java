package User.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

public class ArticleCard extends VBox {

    private Label titleLabel;
    private Label dateLabel;
    private ImageView articleImage;

    private int id;
    private String title;
    private String content;
    private String imagePath;
    private LocalDate publishDate;
    private Runnable onOpenDetailCallback; // Callback for opening detail view

    public ArticleCard(int id, String title, String content, String imagePath, LocalDate publishDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.publishDate = publishDate;

        initializeUI();
    }

    private void initializeUI() {
        titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        dateLabel = new Label("Published: " + publishDate.toString());

        VBox contentBox = new VBox();
        contentBox.setSpacing(5);

        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                InputStream stream = getClass().getResourceAsStream(imagePath);
                if (stream == null) {
                    throw new IOException("Image not found: " + imagePath);
                }
                Image image = new Image(stream);
                if (image.isError()) {
                    throw new IOException("Failed to load image: " + imagePath);
                }
                articleImage = new ImageView(image);
                articleImage.setFitWidth(500); // Adjust size as needed
                articleImage.setPreserveRatio(true);
                contentBox.getChildren().add(articleImage);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle image loading error
            }
        }

        contentBox.getChildren().addAll(titleLabel, dateLabel);

        contentBox.setPadding(new Insets(10));
        contentBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc;");

        getChildren().add(contentBox);

        // Set action for clicking the card to open detail view
        setOnMouseClicked(event -> {
            if (onOpenDetailCallback != null) {
                onOpenDetailCallback.run();
            }
        });
    }

    public void setOnOpenDetail(Runnable callback) {
        this.onOpenDetailCallback = callback;
    }
}
