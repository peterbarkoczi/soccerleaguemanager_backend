package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {

    League findFirstByName(String name);

    League findFirstById(Long id);

    List<League> findLeaguesByLocation_IdOrderByIdAsc(Long locationId);

}
