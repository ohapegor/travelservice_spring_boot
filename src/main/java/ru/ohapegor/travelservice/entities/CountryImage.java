package ru.ohapegor.travelservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "country_image")
public class CountryImage extends AbstractBaseEntity {

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "image")
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "CountryImage{" +
                "id=" + id +
                ", countryId=" + countryId +
                '}';
    }
}
