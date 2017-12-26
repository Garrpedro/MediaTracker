package project.cm.mediatracker.Model;

/**
 * Created by pedrog on 26-12-2017.
 */

public class Episode {

    private Integer season;
    private Integer episode;
    private String title;
    private String released;
    private Integer rating;

    public Episode(Integer season, Integer episode, String title, String released, Integer rating) {
        this.season = season;
        this.episode = episode;
        this.title = title;
        this.released = released;
        this.rating = rating;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "season=" + season +
                ", episode=" + episode +
                ", title='" + title + '\'' +
                ", released='" + released + '\'' +
                '}';
    }
}
