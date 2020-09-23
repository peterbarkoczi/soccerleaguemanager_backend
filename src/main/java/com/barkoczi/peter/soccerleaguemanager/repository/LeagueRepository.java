package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {

    League findByNameAndLocation_Id(String name, Long locationId);

    League findFirstById(Long id);

    List<League> findLeaguesByLocation_IdOrderByIdAsc(Long locationId);

}
