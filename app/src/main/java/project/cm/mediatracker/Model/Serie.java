package project.cm.mediatracker.Model;

import java.util.List;

/**
 * Created by pedrog on 26-12-2017.
 */

public class Serie extends Content {

    private String actors;
    private Integer totalSeason;
    private List<Episode> episodes;

    public Serie(Integer codContent, String title, Integer year, Integer runtime, String genre, String writer, String plot, String poster, String actors, Integer totalSeason, List<Episode> episodes) {
        super(codContent, title, year, runtime, genre, writer, plot, poster);
        this.actors = actors;
        this.totalSeason = totalSeason;
        this.episodes = episodes;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public Integer getTotalSeason() {
        return totalSeason;
    }

    public void setTotalSeason(Integer totalSeason) {
        this.totalSeason = totalSeason;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "actors='" + actors + '\'' +
                ", totalSeason=" + totalSeason +
                ", episodes=" + episodes +
                '}';
    }
}
