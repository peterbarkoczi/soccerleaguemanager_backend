package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.Leagues;
import com.barkoczi.peter.soccerleaguemanager.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LeagueController {

    @Autowired
    private LeagueRepository leagueRepository;

    @GetMapping("/liga/list")
    public List<Leagues> leagueList() {
        List<Leagues> leagues = leagueRepository.findAllLeague();
        System.out.println(leagues);
        return leagues;
    }
}
