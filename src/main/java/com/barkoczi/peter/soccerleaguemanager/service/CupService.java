package com.barkoczi.peter.soccerleaguemanager.service;


import com.barkoczi.peter.soccerleaguemanager.entity.Cup;
import com.barkoczi.peter.soccerleaguemanager.model.CupDetails;
import com.barkoczi.peter.soccerleaguemanager.repository.CupRepository;
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
public class CupService {

    @Autowired
    private CupRepository cupRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MatchService matchService;

    public Cup createAndSaveNewCup(CupDetails cupDetails) {
        Cup newCup = Cup.builder()
                .name(cupDetails.getName())
                .date(cupDetails.getDate())
                .location(locationRepository.findFirstById(cupDetails.getLocationId()))
                .build();
         cupRepository.saveAndFlush(newCup);

         Cup cup = cupRepository.findCupByName(cupDetails.getName());
         cup.setMatches(matchService.createMatches(cupDetails.getTeamList(), newCup));

        return newCup;
    }
}
