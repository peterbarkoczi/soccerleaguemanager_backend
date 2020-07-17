package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.Leagues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeagueRepository extends JpaRepository<Leagues, Long> {

    @Query("SELECT " +
            "new com.barkoczi.peter.soccerleaguemanager.entity.Leagues(id, name)" +
            "FROM Leagues")
    List<Leagues> findAllLeague();



}
