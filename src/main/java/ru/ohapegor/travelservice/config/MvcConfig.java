package ru.ohapegor.travelservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("hello");
        registry.addViewController("/index").setViewName("hello");
        registry.addViewController("/index.html").setViewName("hello");
        registry.addViewController("/").setViewName("hello");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/contacts").setViewName("contacts");
        registry.addViewController("/info").setViewName("info");
        registry.addViewController("/news").setViewName("news");
        registry.addViewController("/tours").setViewName("tours");
    }

}