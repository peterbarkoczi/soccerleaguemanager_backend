package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.Cup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CupRepository extends JpaRepository<Cup, Long> {

    Cup findCupByName(String name);

    Cup findCupById(Long id);

    List<Cup> findCupsByLocationIdOrderByIdAsc(Long locationId);

}
