package project.cm.mediatracker.Model;

/**
 * Created by pedrog on 08-01-2018.
 */

public class MediaContent {

    private String imdbId;
    private String type;
    private String list;

    public MediaContent(String imdbId, String type, String list) {
        this.imdbId = imdbId;
        this.type = type;
        this.list = list;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
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

    @Override
    public String toString() {
        return "MediaContent{" +
                "imdbId='" + imdbId + '\'' +
                ", type='" + type + '\'' +
                ", list='" + list + '\'' +
                '}';
    }
}
