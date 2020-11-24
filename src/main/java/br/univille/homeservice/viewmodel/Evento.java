package br.univille.homeservice.viewmodel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Evento {
    private long id;
    private String title;
    private String start;
    private String end;
    private String description;

    public Evento(long id, String title, String start, String end, String description) {
        this.id = id;
        this.title = title;

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'-0300'");
            this.start = formato2.format(formato.parse(start));
            this.end =  formato2.format(formato.parse(end));
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.description = description;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
