package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.Cup;
import com.barkoczi.peter.soccerleaguemanager.model.CupDetails;
import com.barkoczi.peter.soccerleaguemanager.service.CupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class CupController {

    @Autowired
    private CupService cupService;

    @PostMapping("/cups/create_cup")
    public Cup createNewCup(@RequestBody CupDetails cupDetails) {
        return cupService.createAndSaveNewCup(cupDetails);
    }

}
