package project.cm.mediatracker.Model;

/**
 * Created by pedrog on 08-01-2018.
 */

public class MediaContent {

    private String title;
    private String year;
    private String poster;
    private String type;
    private String list;

    public MediaContent() {
    }

    public MediaContent(String title, String year, String poster, String type, String list) {
        this.title = title;
        this.year = year;
        this.poster = poster;
        this.type = type;
        this.list = list;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

}
