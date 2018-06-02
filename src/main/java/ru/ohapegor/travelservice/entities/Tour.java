package ru.ohapegor.travelservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Table(name = "tour")
public class Tour extends AbstractBaseEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "hotel")
    private String hotel;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "image_href")
    private String imageHref;

    @Column(name = "min_price")
    private Integer minPrice;

    @Column(name = "duration")
    private String duration;

    @Column(name = "for_room")
    private String forRoom;

    @Column(name = "updated", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private ZonedDateTime updated = ZonedDateTime.now();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public String printUpdated(){
       return updated.withZoneSameInstant(ZoneId.of("Europe/Moscow"))
               .format(DateTimeFormatter.ofPattern("MM-dd HH:mm:ss"));
    }

    public String printDate(){
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy",new Locale("ru")));
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getForRoom() {
        return forRoom;
    }

    public void setForRoom(String for_room) {
        this.forRoom = for_room;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", hotel='" + hotel + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", imageHref='" + imageHref + '\'' +
                ", minPrice=" + minPrice +
                ", duration='" + duration + '\'' +
                ", forRoom='" + forRoom + '\'' +
                ", updated=" + updated +
                ", id=" + id +
                '}';
    }
}
