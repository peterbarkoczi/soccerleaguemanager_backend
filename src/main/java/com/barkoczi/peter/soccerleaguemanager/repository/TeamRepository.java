package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Set<Team> findTeamsByLocation_IdOrderByNameAsc(Long id);

    Team findFirstById(Long id);
}
