package ru.ohapegor.travelservice.service.loader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ohapegor.travelservice.entities.Tour;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.ohapegor.travelservice.util.LoggingUtil.enterInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.errorInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.exitInfo;


@Service
public class TourLoader {

    private static final Logger LOG = LoggerFactory.getLogger(TourLoader.class.getSimpleName());

    private final PhantomJsUtils phantomJsUtils;

    private static final String URL = "https://kirov.ross-tur.ru/";
    private static final String ELEMENT_CLASS = "tourElement";
    private static final String DATE_ATTR = "data-b";
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String PRICE_CLASS = "price";

    @Autowired
    public TourLoader(PhantomJsUtils phantomJsUtils) {
        this.phantomJsUtils = phantomJsUtils;
    }

    public List<Tour> getToursFromSite() {
        LOG.info(enterInfo());
        List<Tour> tours = null;
        try {
            Document doc = phantomJsUtils.renderPage(URL, 25);
            Objects.requireNonNull(doc, "Null document");
            Elements elements = doc.getElementsByClass(ELEMENT_CLASS);
            tours = elements.stream().map(this::parseElem).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            throw new ParseHtmlException(e);
        }
        LOG.info(exitInfo());
        return tours;
    }

    private Tour parseElem(Element element) {
        LOG.info(enterInfo());
        Tour tour = new Tour();
        try {
            //Date
            LocalDate date = LocalDate.parse(element.attr(DATE_ATTR), DateTimeFormatter.ofPattern(DATE_PATTERN));
            tour.setDate(date);

            //price
            Element price = element.getElementsByClass(PRICE_CLASS).first();
            tour.setMinPrice(Integer.parseInt(price.text().replaceAll(" ", "")));

            Elements pList = element.getElementsByTag("p");

            //hotel
            String hotel = pList.get(1).text();
            tour.setHotel(hotel);

            //country
            String country = pList.get(2).text().split(",")[0].trim();
            tour.setCountry(country);

            //city
            String city = pList.get(2).text().split(",")[1].trim();
            tour.setCity(city);

            //duration
            String duration = pList.get(3).text();
            tour.setDuration(duration);

            //forRoom
            String forRoom = pList.get(4).text();
            tour.setForRoom(forRoom);

            //image href
            String img = element.getElementsByTag("img").first().attr("src");
            img = checkURL(img);
            tour.setImageHref(img);
        } catch (Exception e) {
            LOG.error(errorInfo(e)+"\n Element = " + element);
            tour = null;
        }
        LOG.info(exitInfo());
        return tour;
    }

    private String checkURL(String img) {
        if (img.startsWith("http")) return img;

        if (img.startsWith("/")){
           return img = "https:"+img;
        }

        else {
          return   img = "https://"+img;
        }
    }
}
