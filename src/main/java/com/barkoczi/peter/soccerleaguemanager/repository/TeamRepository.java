package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
