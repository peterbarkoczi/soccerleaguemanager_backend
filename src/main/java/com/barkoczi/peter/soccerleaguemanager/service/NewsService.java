package com.barkoczi.peter.soccerleaguemanager.service;

import com.barkoczi.peter.soccerleaguemanager.entity.News;
import com.barkoczi.peter.soccerleaguemanager.repository.LocationRepository;
import com.barkoczi.peter.soccerleaguemanager.repository.NewsRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private LocationRepository locationRepository;

    public News createAndSaveNewNews(News news, String locationName) {
        News tempNews = News.builder()
                .posted(news.getPosted())
                .title(news.getTitle())
                .description(news.getDescription())
                .news(news.getNews())
                .location(locationRepository.findLocationByName(locationName))
                .build();

        newsRepository.saveAndFlush(tempNews);

        return tempNews;
    }

    public List<News> getNewsByLocation(String locationName) {
        return newsRepository.findAllByLocationOrderByPosted(locationRepository.findLocationByName(locationName));
    }

    @Transactional
    public void editNews(News news) {
        newsRepository.updateNewsById(news.getTitle(), news.getDescription(), news.getNews(), news.getId());
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
