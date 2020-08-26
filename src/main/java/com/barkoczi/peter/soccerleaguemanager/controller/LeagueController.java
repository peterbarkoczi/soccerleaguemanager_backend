package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import com.barkoczi.peter.soccerleaguemanager.model.LeagueDetails;
import com.barkoczi.peter.soccerleaguemanager.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/league/get_league_list/{locationId}")
    public List<League> getLeagueList(@PathVariable("locationId") Long locationId) {
        return leagueService.getLeagueListByLocationId(locationId);
    }

    @PostMapping("/league/create_league")
    public League addNewLeague(@RequestBody LeagueDetails leagueDetails) {
        return leagueService.createAndSaveNewLeague(leagueDetails);
    }
}
