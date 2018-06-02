package ru.ohapegor.travelservice.service.loader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static ru.ohapegor.travelservice.util.LoggingUtil.enterInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.exitInfo;

@Service
public class PhantomJsUtils {

    private static final Logger logger = LoggerFactory.getLogger(PhantomJsUtils.class);

    private PhantomJSDriver workingDriver;

    public Document renderPage(String url, int timeout) {
        logger.info(enterInfo());
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        logger.info("os name :"+System.getenv("OS"));
        if (!System.getenv("OS").toLowerCase().contains("win")) {
            caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/local/share/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
        }else {
            caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "c:\\Soft\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<Document> future = executor.submit(() -> {
                int count = 0;
                do {
                    try {
                        logger.info("Iteration - " + ++count);
                        PhantomJSDriver ghostDriver = null;
                        if (workingDriver == null) {
                            logger.info(">>>Creating new driver");
                            ghostDriver = new PhantomJSDriver(caps);
                        } else {
                            logger.info(">>>Using  successful driver");
                            ghostDriver = workingDriver;
                        }
                        ghostDriver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
                        ghostDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                        ghostDriver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
                        ghostDriver.get(url);
                        Document doc = Jsoup.parse(ghostDriver.getPageSource());
                        if (
                                doc.getElementsByClass("tourElement").size() >= 10 ||
                                        doc.getElementsByClass("news-list__item-wrap").size() >= 10 ||
                                        doc.getElementsByTag("article").size() >= 10
                                ) {
                            logger.info("Successful iteration - " + count);
                            workingDriver = ghostDriver;
                            return doc;
                        } else {
                            closeDriver(ghostDriver);
                            workingDriver = null;
                        }
                    }catch (Exception e){
                        logger.error(e.toString());
                        workingDriver = null;
                    }
                } while (!Thread.currentThread().isInterrupted() && count < 20);
                throw new NotFoundException("Failed loading site, iterations - " + count);
            });
            return future.get();
        } catch (Exception e) {
            logger.error(e.toString(),e);
            executor.shutdownNow();
        }finally {
            executor.shutdownNow();
        }
        logger.info(exitInfo());
        return null;
    }

    private void closeDriver(WebDriver driver){
        try {
            driver.close();
        }catch (Exception ignored){

        }

        try {
            driver.quit();
        }catch (Exception ignored){

        }
    }

}
