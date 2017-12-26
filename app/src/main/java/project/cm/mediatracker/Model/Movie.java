package project.cm.mediatracker.Model;

/**
 * Created by pedrog on 26-12-2017.
 */

public class Movie extends Content {



    public Movie(Integer codContent, String title, Integer year, Integer runtime, String genre, String writer, String plot, String poster) {
        super(codContent, title, year, runtime, genre, writer, plot, poster);
    }
}
