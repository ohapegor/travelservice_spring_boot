package ru.ohapegor.travelservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("ru.ohapegor.travelservice")
@EnableJpaRepositories("ru.ohapegor.travelservice.dao")
@EntityScan("ru.ohapegor.travelservice.entities")
public class TravelserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelserviceApplication.class, args);
    }
}
