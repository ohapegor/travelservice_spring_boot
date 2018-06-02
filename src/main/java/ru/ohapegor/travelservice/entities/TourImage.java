package ru.ohapegor.travelservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;

@Entity
@Table(name = "tour_image")
public class TourImage extends AbstractBaseEntity {

    @Column(name = "tour_id")
    private Long tourId;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TourImage tourImage = (TourImage) o;

        if (getId() != null ? !getId().equals(tourImage.getId()) : tourImage.getId() != null) return false;
        if (tourId != null ? !tourId.equals(tourImage.tourId) : tourImage.tourId != null) return false;
        if (name != null ? !name.equals(tourImage.name) : tourImage.name != null) return false;
        return Arrays.equals(image, tourImage.image);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (tourId != null ? tourId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "TourImage{" +
                "id=" + getId() +
                ", tourId=" + tourId +
                ", name='" + name + '\'' +
                '}';
    }
}
