package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.Location;
import com.barkoczi.peter.soccerleaguemanager.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByLocationOrderByPosted(Location location);

    @Query("update News set title = :title, description = :description, news = :news where id = :newsId")
    @Modifying(clearAutomatically = true)
    void updateNewsById(@Param("title") String title,
                        @Param("description") String description,
                        @Param("news") String news,
                        @Param("newsId") Long newsId);

}
