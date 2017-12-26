package project.cm.mediatracker.Model;

/**
 * Created by pedrog on 26-12-2017.
 */

public class Movie extends Content {

    private String releasedDate;
    private String director;
    private String actors;

    public Movie(Integer codContent, String title, Integer year, Integer runtime, String genre, String writer, String plot, String poster, String releasedDate, String director, String actors) {
        super(codContent, title, year, runtime, genre, writer, plot, poster);
        this.releasedDate = releasedDate;
        this.director = director;
        this.actors = actors;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "releasedDate='" + releasedDate + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                '}';
    }
}
