package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {

    League findFirstById(Long id);

    League findLeagueByLocationNameAndName(String locationName, String name);

    List<League> findLeaguesByLocationNameOrderById(String shortName);

}
