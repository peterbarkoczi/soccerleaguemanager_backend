package com.barkoczi.peter.soccerleaguemanager.service;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import com.barkoczi.peter.soccerleaguemanager.entity.Location;
import com.barkoczi.peter.soccerleaguemanager.model.LeagueDetails;
import com.barkoczi.peter.soccerleaguemanager.repository.LeagueRepository;
import com.barkoczi.peter.soccerleaguemanager.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class LeagueService {

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private LocationRepository locationRepository;

    public League createAndSaveNewLeague(LeagueDetails leagueDetails) {
        League newLeague = League.builder()
                .name(leagueDetails.getName())
                .location(locationRepository.findFirstById(leagueDetails.getLocationId()))
                .build();

        leagueRepository.saveAndFlush(newLeague);
        return newLeague;
    }

}
