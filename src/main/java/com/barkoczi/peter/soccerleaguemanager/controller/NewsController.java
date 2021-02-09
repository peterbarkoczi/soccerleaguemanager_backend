package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.News;
import com.barkoczi.peter.soccerleaguemanager.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/get_location_news")
    public List<News> getNewsByLocation(@RequestParam String locationName) {
        return newsService.getNewsByLocation(locationName);
    }

    @PostMapping("/add_new")
    public News createNews(@RequestBody News news, @RequestParam String locationName) {
        return newsService.createAndSaveNewNews(news, locationName);
    }

    @PatchMapping("/edit")
    public void editNews(@RequestBody News news) {
        newsService.editNews(news);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return "News deleted";
    }

}
