package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaguesRepository extends JpaRepository<League, Long> {

    League findFirstByName(String name);

}
