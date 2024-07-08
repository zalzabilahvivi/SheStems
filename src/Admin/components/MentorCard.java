package Admin.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;

public class MentorCard extends HBox {

    private ImageView mentorImage;
    private VBox detailsBox; // VBox for details including name, profession, rating, bio

    public MentorCard(String imageUrl, String name, String profession, String rating, String bio) {
        setSpacing(10); // Set spacing between elements in the HBox

        mentorImage = new ImageView();
        mentorImage.setFitWidth(100); // Adjust size as needed
        mentorImage.setPreserveRatio(true);

        detailsBox = new VBox();
        detailsBox.setSpacing(5);

        Label nameLabel = new Label(name);
        Label professionLabel = new Label(profession);
        Label ratingLabel = new Label(rating);
        Label bioLabel = new Label(bio);

        detailsBox.getChildren().addAll(nameLabel, professionLabel, ratingLabel, bioLabel);

        getChildren().addAll(mentorImage, detailsBox);

        // Load mentor image
        try {
            InputStream stream = getClass().getResourceAsStream(imageUrl);
            if (stream == null) {
                throw new IOException("Image not found: " + imageUrl);
            }
            Image image = new Image(stream);
            if (image.isError()) {
                throw new IOException("Failed to load image: " + imageUrl);
            }
            mentorImage.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle image loading error
        }

        // Set styling
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc;");
    }
}
