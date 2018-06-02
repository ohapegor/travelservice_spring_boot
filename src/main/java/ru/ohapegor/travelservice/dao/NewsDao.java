package ru.ohapegor.travelservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ohapegor.travelservice.entities.NewsItem;

public interface NewsDao extends JpaRepository<NewsItem, Long> {
}
