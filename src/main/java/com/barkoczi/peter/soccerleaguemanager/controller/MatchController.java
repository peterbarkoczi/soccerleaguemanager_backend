package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.Match;
import com.barkoczi.peter.soccerleaguemanager.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/match/update_score")
    public void updateScore(@RequestBody Match match) {
        matchService.updateScore(match);
    }
}
