package br.univille.homeservice.viewmodel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evento {
    private long id;
    private String title;
    private Date start;
    private Date end;
    private String description;

    public Evento(long id, String title, Date start, Date end, String description) {
        this.id = id;
        this.title = title;

        this.start = start;
        this.end =  end;
        /*
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'-0300'");
            //this.start = formato2.format(formato.format(start));
            //this.end =  formato2.format(formato.parse(end));

           
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        */

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

    public Date getStart() {
        return this.start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
