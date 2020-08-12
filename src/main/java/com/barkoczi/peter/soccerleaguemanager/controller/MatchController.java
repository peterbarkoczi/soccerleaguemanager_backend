package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.Match;
import com.barkoczi.peter.soccerleaguemanager.model.CardDetails;
import com.barkoczi.peter.soccerleaguemanager.repository.MatchRepository;
import com.barkoczi.peter.soccerleaguemanager.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/match/get_qualifiers")
    public List<Match> getQualifiers(@RequestParam Long cupId, String matchType) {
        return matchRepository.findAllByCupIdAndMatchType(cupId, matchType);
    }

    @GetMapping("/match/get_semifinals")
    public List<Match> getSemiFinals(@RequestParam Long cupId, String matchType) {
        return matchRepository.findAllByCupIdAndMatchType(cupId, matchType);
    }

    @GetMapping("/match/create_semi_finals")
    public List<Match> createSemiFinals(@RequestParam Long cupId, String matchType) {
        return matchService.createSemiFinals(cupId, matchType);
    }

    @PostMapping("/match/update_score")
    public void updateScore(@RequestBody Match match) {
        matchService.updateScore(match);
    }

    @PostMapping("match/update_finished")
    public void updateFinished(@RequestBody Match match) {
        matchService.setFinished(match);
    }

    @PatchMapping("/match/update_card")
    public void updateCard(@RequestBody CardDetails cardDetails) {
        matchService.updateCard(cardDetails);
    }
}
