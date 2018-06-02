package ru.ohapegor.travelservice.service.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ohapegor.travelservice.dao.NewsDao;
import ru.ohapegor.travelservice.entities.NewsItem;
import ru.ohapegor.travelservice.service.loader.NewsLoader;

import javax.annotation.PostConstruct;
import java.util.List;

import static ru.ohapegor.travelservice.util.LoggingUtil.enterInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.exitInfo;

@Service
public class NewsService {

    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private NewsLoader newsLoader;

    @PostConstruct
    void postConstruct() {
        logger.info(enterInfo());
        logger.info("reload = " + reloadNews());
    }

    public List<NewsItem> getAll() {
        logger.info(enterInfo());
        List<NewsItem> news = newsDao.findAll();
        news.sort((n1, n2) -> n2.getDateTime().compareTo(n1.getDateTime()));
        logger.info(exitInfo());
        return news;
    }

    public boolean reloadNews() {
        logger.info(enterInfo());
        List<NewsItem> news = newsLoader.getNewsFromSite();
        //logger.warn(news.toString());
        boolean success = false;
        if (news.size() > 10) {
            newsDao.deleteAll();
            success = newsDao.saveAll(news).size() > 0;
            logger.info("Success = " + success);
        }
        logger.info(exitInfo("Success = " + success));
        return success;
    }

}
