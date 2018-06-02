package ru.ohapegor.travelservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.ohapegor.travelservice.service.news.NewsService;
import ru.ohapegor.travelservice.service.tour.TourService;

import static ru.ohapegor.travelservice.util.LoggingUtil.enterInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.exitInfo;

@Service
public class ScheduledLoader {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledLoader.class);

    @Autowired
    private TourService tourService;

    @Autowired
    private NewsService newsService;

    @Scheduled(cron = "30 */15 * * * *")
    public void fillDb(){
        logger.info(enterInfo());
        tourService.reloadTours();
        logger.info(exitInfo());
    }

    @Scheduled(cron = "0 */45 * * * *")
    public void loadNews(){
        logger.info(enterInfo());
        newsService.reloadNews();
        logger.info(exitInfo());
    }
}
