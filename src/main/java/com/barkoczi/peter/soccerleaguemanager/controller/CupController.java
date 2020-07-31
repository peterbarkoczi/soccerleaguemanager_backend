package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.Cup;
import com.barkoczi.peter.soccerleaguemanager.model.CupDetails;
import com.barkoczi.peter.soccerleaguemanager.repository.CupRepository;
import com.barkoczi.peter.soccerleaguemanager.service.CupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
public class CupController {

    @Autowired
    private CupService cupService;

    @Autowired
    private CupRepository cupRepository;


    @PostMapping("/cups/create_cup")
    public Cup createNewCup(@RequestBody CupDetails cupDetails) {
        return cupService.createAndSaveNewCup(cupDetails);
    }

    @GetMapping("/cups/get_matches")
    public Cup getCup(@RequestParam Long cupId) {
        return cupRepository.findCupById(cupId);
    }

    @GetMapping("/cups/list")
    public List<Cup> getCupsList() {
        return cupRepository.findAllByOrderByIdAsc();
    }

}
