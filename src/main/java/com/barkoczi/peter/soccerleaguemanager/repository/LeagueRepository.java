package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {

    League findFirstById(Long id);

    League findLeagueByName(String name);

    League findLeagueByLocationNameAndName(String locationName, String name);

    List<League> findLeaguesByLocation_IdOrderByIdAsc(Long locationId);

    List<League> findLeaguesByLocationNameOrderById(String shortName);

}
