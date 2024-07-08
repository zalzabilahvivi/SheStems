package Admin;

public class Mentoring {
    private String theme;
    private String mentee1;
    private String zoomLink;
    private double rating;
    private String job;
    private String date;

    public Mentoring(String theme, String mentee1, String zoomLink, double rating, String job, String date) {
        this.theme = theme;
        this.mentee1 = mentee1;
        this.zoomLink = zoomLink;
        this.rating = rating;
        this.job = job;
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public String getMentee1() {
        return mentee1;
    }

    public String getZoomLink() {
        return zoomLink;
    }

    public double getRating() {
        return rating;
    }

    public String getJob() {
        return job;
    }

    public String getDate() {
        return date;
    }
}
