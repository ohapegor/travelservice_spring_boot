package ru.ohapegor.travelservice.service.tour;

import org.springframework.web.multipart.MultipartFile;
import ru.ohapegor.travelservice.entities.Tour;
import ru.ohapegor.travelservice.entities.TourImage;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();

    Tour getTourById(Long id);

    Tour getTourByName(String name);

    Tour create(Tour tour);

    TourImage getTourImageById(Long id);

    Tour save(Tour tour);

    boolean deleteTours();

    boolean deleteTourById(Long id);

    boolean deleteImages();

    boolean reloadTours();

    boolean storeImg(Long id, MultipartFile file);
}
