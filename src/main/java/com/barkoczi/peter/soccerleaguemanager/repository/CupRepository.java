package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.Cup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupRepository extends JpaRepository<Cup, Long> {

    Cup findCupByName(String name);

}
