package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import com.barkoczi.peter.soccerleaguemanager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findTeamsByLocationNameOrderByNameAsc(String locationName);

    List<Team> findTeamsByLeagueOrderByNameAsc(League league);

    Team findFirstById(Long id);

    Team findByName(String name);

    Team findTeamByLocationNameAndName(String locationName, String name);
}
