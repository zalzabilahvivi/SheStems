package Admin.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScheduleCard extends HBox {

    public static void loadMentoringSchedule(HBox scrollableContainer) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Admin/mentoring.csv"))) {
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue; // skip header line
                }
                ScheduleCard scheduleCard = createFromCSV(line);
                if (scheduleCard != null) {
                    scrollableContainer.getChildren().add(scheduleCard);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ScheduleCard(String theme, String mentee1, String zoomLink, double rating, String job, String date) {
        super();
        initialize(theme, mentee1, zoomLink, rating, job, date);
    }

    private void initialize(String theme, String mentee1, String zoomLink, double rating, String job, String date) {
        // Create VBox for each mentoring schedule
        VBox contentBox = new VBox();
        contentBox.setSpacing(5);

        Label themeLabel = new Label("Theme: " + theme);
        Label menteeLabel = new Label("Mentees: " + mentee1);
        Label zoomLinkLabel = new Label("Zoom Link: " + zoomLink);
        Label ratingLabel = new Label("Rating: " + rating);
        Label jobLabel = new Label("Job: " + job);
        Label dateLabel = new Label("Date: " + date);

        contentBox.getChildren().addAll(themeLabel, menteeLabel, zoomLinkLabel, ratingLabel, jobLabel, dateLabel);

        // Styling for ScheduleCard
        contentBox.setPadding(new Insets(10));
        contentBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc;");

        getChildren().add(contentBox);
    }

    private static ScheduleCard createFromCSV(String csvLine) {
        try {
            String[] data = csvLine.split(",");
            String theme = data[0];
            String mentee1 = data[1];
            String zoomLink = data[2];
            double rating = Double.parseDouble(data[3]);
            String job = data[4];
            String date = data[5];

            return new ScheduleCard(theme, mentee1, zoomLink, rating, job, date);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }
}
