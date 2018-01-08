package project.cm.mediatracker.Model;

/**
 * Created by pedrog on 26-12-2017.
 */

public class Content {

    private String codContent;
    private String title;
    private String year;
    private Integer runtime;
    private String genre;
    private String writer;
    private String plot;
    private String poster;

    public Content(String title, String year, String poster) {
        this.title = title;
        this.year = year;
        this.poster = poster;
    }

    public Content(String codContent, String title, String year, String poster) {
        this.codContent = codContent;
        this.title = title;
        this.year = year;
        this.poster = poster;
    }

    public Content(String codContent, String title, String year, Integer runtime, String genre, String writer, String plot, String poster) {
        this.codContent = codContent;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.genre = genre;
        this.writer = writer;
        this.plot = plot;
        this.poster = poster;
    }

    public String getCodContent() {
        return codContent;
    }

    public void setCodContent(String codContent) {
        this.codContent = codContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Content{" +
                "codContent=" + codContent +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", runtime=" + runtime +
                ", genre='" + genre + '\'' +
                ", writer='" + writer + '\'' +
                ", plot='" + plot + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
