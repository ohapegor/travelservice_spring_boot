package ru.ohapegor.travelservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.ohapegor.travelservice.entities.NewsItem;
import ru.ohapegor.travelservice.entities.Tour;
import ru.ohapegor.travelservice.service.news.NewsService;
import ru.ohapegor.travelservice.service.tour.TourService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static ru.ohapegor.travelservice.util.LoggingUtil.enterInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.exitInfo;

@Controller
public class RootController {

    private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private TourService tourService;

    @Autowired
    private NewsService newsService;


    @GetMapping("tours")
    public ModelAndView hotTours() {
        logger.debug(enterInfo());
        ModelAndView modelAndView = new ModelAndView("tours", "tours", tourService.getAllTours());
        modelAndView.addObject("from","tours");
        logger.debug(exitInfo());
        return modelAndView;
    }

    @GetMapping("news")
    public ModelAndView news() {
        logger.debug(enterInfo());
        ModelAndView modelAndView = new ModelAndView("news", "news", newsService.getAll());
        modelAndView.addObject("from","news");
        logger.debug(exitInfo());
        return modelAndView;
    }

}
