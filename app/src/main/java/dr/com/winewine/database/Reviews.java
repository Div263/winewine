package dr.com.winewine.database;

/**
 * Created by ruchadeshmukh on 2/7/15.
 */
public class Reviews {
    private int stars;
    private String comments;
    private String recommendations;

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
}
