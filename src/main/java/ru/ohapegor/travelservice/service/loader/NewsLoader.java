package ru.ohapegor.travelservice.service.loader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.ohapegor.travelservice.entities.NewsItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.ohapegor.travelservice.util.LoggingUtil.enterInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.exitInfo;

@Service
public class NewsLoader {

    private static final Logger logger = LoggerFactory.getLogger(TourLoader.class.getSimpleName());

    // private static final String URL = "https://www.tourprom.ru/news/";
    private static final String URL = "http://news.turizm.ru/";
    private static final String ELEMENT_CLASS = "ts_item";
    private static final String DATE_TAG = "time";
    private static final String TITLE_TAG = "h3";
    private static final String TEXT_CLASS = "ts_txt";


    public List<NewsItem> getNewsFromSite() {
        logger.info(enterInfo(URL));
        List<NewsItem> news = null;
        try {
            //Document doc = PhantomJsUtils.renderPage(URL.toString(),20);
            Document doc = Jsoup.connect(URL).get();
            Objects.requireNonNull(doc, "Null document");
            // System.out.println(doc);
            Elements elements = doc.getElementsByClass(ELEMENT_CLASS);
            news = elements.stream()
                    .map(this::parseElem)
                    // .peek(System.out::println)
                    .collect(Collectors.toList());
            logger.info(exitInfo("news size = " + news.size()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ParseHtmlException(e);
        }
        logger.info(exitInfo());
        return news;
    }

    private NewsItem parseElem(Element element) {
        logger.info(enterInfo());
        NewsItem newsItem = null;
        //     System.out.println(element);
        try {
            //DateTime
            Element dateElement = element.getElementsByTag(DATE_TAG).first();
            String date = dateElement.attr("datetime");
            logger.info("datetime = " + date);
            LocalDateTime localDateTime = parseTime(date);

            //Title
            Element titleElement = element.getElementsByTag(TITLE_TAG).first();
            String title = titleElement.text();

            //Text
            Element textElement = element.getElementsByClass(TEXT_CLASS).first();
            String text = textElement.ownText();

            //Href
            String href = textElement.getElementsByTag("a").first().attr("href").trim();

            //Image
            Element imageElem = element.getElementsByTag("img").first();
            String imageHref = imageElem.attr("src").trim();

            newsItem = new NewsItem();
            newsItem.setDateTime(localDateTime);
            newsItem.setTitle(title);
            newsItem.setText(text);
            newsItem.setImageHref(imageHref);
            newsItem.setHref(href);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info(exitInfo());
        return newsItem;
    }


    private LocalDateTime parseTime(String timeStr) {
        String pattern1 = "yyyy-MM-dd'T'HH:mm";
        String pattern2 = "yyyy-MM-dd HH:mm";
        try {
            return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern1));
        } catch (Exception e) {
            logger.info(pattern1 + " - invalid pattern for " + timeStr);
        }

        try {
            return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern2));
        } catch (Exception e) {
            logger.info(pattern2 + " - invalid pattern for " + timeStr);
        }

        return null;
    }
}
