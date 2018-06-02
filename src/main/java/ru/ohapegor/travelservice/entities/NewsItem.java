package ru.ohapegor.travelservice.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "news")
public class NewsItem extends AbstractBaseEntity {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime dateTime;

    @Column(name = "text", nullable = false,length = 2000)
    private String text;

    @Column(name = "image_href", nullable = false)
    private String imageHref;

    @Column(name = "href", nullable = false)
    private String href;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String printDate(){
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM"));
    }

    public String printTime(){
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "\n\ttitle='" + title + '\'' +
                ", \n\tdateTime=" + dateTime +
                ", \n\ttext='" + text + '\'' +
                ", \n\timageHref='" + imageHref + '\'' +
                ", \n\thref='" + href + '\'' +
                ", \n\tid=" + id +
                "\n}";
    }
}
