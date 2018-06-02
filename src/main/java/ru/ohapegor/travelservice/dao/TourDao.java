package ru.ohapegor.travelservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ohapegor.travelservice.entities.Tour;

public interface TourDao extends JpaRepository<Tour, Long> {

    Tour findByName(String name);
}
