package ru.ohapegor.travelservice.service.tour;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ohapegor.travelservice.dao.TourDao;
import ru.ohapegor.travelservice.dao.TourImageDao;
import ru.ohapegor.travelservice.entities.Country;
import ru.ohapegor.travelservice.entities.Tour;
import ru.ohapegor.travelservice.entities.TourImage;
import ru.ohapegor.travelservice.service.loader.ParseHtmlException;
import ru.ohapegor.travelservice.service.loader.TourLoader;
import ru.ohapegor.travelservice.util.TourUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ru.ohapegor.travelservice.util.LoggingUtil.enterInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.errorInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.exitInfo;

@Service
public class TourServiceImpl implements TourService {

    private static final Logger logger = LoggerFactory.getLogger(TourServiceImpl.class);


    private final TourDao tourDao;

    private final TourImageDao tourImageDao;

    private final TourLoader tourLoader;

    @PostConstruct
    void postConstruct(){
        logger.info(enterInfo());
        logger.info("reload = "+reloadTours());
    }

    @Autowired
    public TourServiceImpl(TourDao tourDao, TourImageDao tourImageDao, TourLoader tourLoader) {
        this.tourDao = tourDao;
        this.tourImageDao = tourImageDao;
        this.tourLoader = tourLoader;
    }

    @Override
    public List<Tour> getAllTours() {
        logger.info(enterInfo());
        List<Tour> tours = tourDao.findAll();
        tours.sort(Comparator.comparing(Tour::getDate));
        logger.info(exitInfo());
        return tours;
    }

    @Override
    public Tour getTourById(Long id) {
        logger.info(enterInfo());
        Objects.requireNonNull(id, "Id is null");
        Tour tour = tourDao.getOne(id);
        logger.info(exitInfo());
        return tour;
    }


    @Override
    public Tour getTourByName(String name) {
        logger.info(enterInfo());
        Objects.requireNonNull(name, "name is null");
        Tour tour = tourDao.findByName(name);
        logger.info(exitInfo());
        return tour;
    }

    @Override
    public Tour create(Tour tour) {
        //todo
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public TourImage getTourImageById(Long id) {
        logger.info(enterInfo());
        Objects.requireNonNull(id, "Id is null");
        TourImage image = tourImageDao.findByTourId(id);
        logger.info(exitInfo());
        return image;
    }

    @Override
    public Tour save(Tour tour) {
        logger.info(enterInfo());
        Objects.requireNonNull(tour, "Tour is null");
        Tour created = tourDao.save(tour);
        Objects.requireNonNull(created, "Failed saving tour : " + tour);
        logger.info(exitInfo());
        return created;
    }

    @Override
    public boolean deleteTours() {
        logger.info(enterInfo());
        tourDao.deleteAll();
        boolean success = tourDao.findAll().size() == 0;
        logger.info(exitInfo("Delete tours successful = " + success));
        return success;
    }

    @Override
    public boolean deleteTourById(Long id) {
        logger.info(enterInfo());
        tourDao.deleteById(id);
        boolean success = !tourDao.existsById(id);
        tourImageDao.deleteByTourId(id);
        logger.info(exitInfo("Delete tour[" + id + "] successful = " + success));
        return success;
    }

    @Override
    public boolean deleteImages() {
        logger.info(enterInfo());
        tourImageDao.deleteAll();
        boolean success = tourImageDao.findAll().size() == 0;
        logger.info(exitInfo("Delete images successful = " + success));
        return success;
    }

    @Override
    @Transactional
    public boolean reloadTours() {
        logger.info(enterInfo());

        boolean success = false;
        try {
            List<Tour> tourList = tourLoader.getToursFromSite();
            if (tourList != null) {
                logger.info("Loaded " + tourList.size() + " hot tours from site");
                deleteTours();
                deleteImages();
                success = tourList.stream()
                        .map(Optional::ofNullable)
                        .filter(Optional::isPresent)
                        .noneMatch(tour -> {
                            Tour t = tour.get();

                    return TourUtil.isNew(save(t));
                });
            }
        } catch (ParseHtmlException e) {
            logger.error(errorInfo(e));
        }

        logger.info(exitInfo("success = " + success));
        return success;
    }

    @Override
    public boolean storeImg(Long id, MultipartFile file) {
        logger.info(enterInfo("Id = " + id) + " File = " + file.getName());
        Tour tour = tourDao.findById(id).get();
        TourImage tourImage = tourImageDao.findByTourId(id);
        if (tourImage == null) tourImage = new TourImage();

        tourImage.setTourId(id);
        try {
            tourImage.setImage(file.getBytes());
        } catch (IOException e) {
            logger.error(errorInfo(e));
        }
        tour.setImageHref("/tour/image/"+id);
        tourDao.save(tour);
        tourImageDao.save(tourImage);
        logger.info(exitInfo());
        return true;
    }

/*    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "spring/spring-app.xml",
                "spring/spring-db.xml",
                "spring/spring-mvc.xml"
                );
        TourService ts = ctx.getBean("tourServiceImpl",TourService.class);
        ts.reloadTours();
    }*/
}
