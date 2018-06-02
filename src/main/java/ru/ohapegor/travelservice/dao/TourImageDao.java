package ru.ohapegor.travelservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ohapegor.travelservice.entities.Tour;
import ru.ohapegor.travelservice.entities.TourImage;

public interface TourImageDao extends JpaRepository<TourImage, Long> {

    TourImage findByTourId(Long tourId);

    int deleteByTourId(Long id);
}
