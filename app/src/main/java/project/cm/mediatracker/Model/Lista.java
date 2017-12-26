package project.cm.mediatracker.Model;

import java.util.List;

/**
 * Created by pedrog on 26-12-2017.
 */

public class Lista {

    private Integer codLista;
    private Integer codTypeLista;
    private List<Content> contents;

    public Lista(Integer codLista, Integer codTypeLista, List<Content> contents) {
        this.codLista = codLista;
        this.codTypeLista = codTypeLista;
        this.contents = contents;
    }

    public Integer getCodLista() {
        return codLista;
    }

    public void setCodLista(Integer codLista) {
        this.codLista = codLista;
    }

    public Integer getCodTypeLista() {
        return codTypeLista;
    }

    public void setCodTypeLista(Integer codTypeLista) {
        this.codTypeLista = codTypeLista;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Lista{" +
                "codLista=" + codLista +
                ", codTypeLista=" + codTypeLista +
                ", contents=" + contents +
                '}';
    }
}
