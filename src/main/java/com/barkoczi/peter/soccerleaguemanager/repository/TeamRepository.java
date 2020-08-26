package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findTeamsByLocation_IdOrderByNameAsc(Long id);

    List<Team> findTeamsByLeagueOrderByNameAsc(League league);

    Team findFirstById(Long id);
}
