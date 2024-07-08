package User;

public class Mentor {
    private String imageUrl;
    private String name;
    private String profession;
    private String rating;
    private String bio;

    public Mentor(String imageUrl, String name, String profession, String rating, String bio) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.profession = profession;
        this.rating = rating;
        this.bio = bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
