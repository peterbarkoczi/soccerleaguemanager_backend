package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import com.barkoczi.peter.soccerleaguemanager.model.LeagueDetails;
import com.barkoczi.peter.soccerleaguemanager.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @PostMapping("/league/add_league")
    public League addNewLeague(@RequestBody LeagueDetails leagueDetails) {
        return leagueService.createAndSaveNewLeague(leagueDetails);
    }
}
