package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.Match;
import com.barkoczi.peter.soccerleaguemanager.model.CardDetails;
import com.barkoczi.peter.soccerleaguemanager.model.TeamStat;
import com.barkoczi.peter.soccerleaguemanager.repository.MatchRepository;
import com.barkoczi.peter.soccerleaguemanager.service.match.TeamStatCreator;
import com.barkoczi.peter.soccerleaguemanager.service.match.MatchService;
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

    @Autowired
    private TeamStatCreator teamStatCreator;

    @GetMapping("/match/get_matches")
    public List<Match> getQualifiers(@RequestParam String locationName, String cupName, String matchType) {
        return matchService.getQualifiersByLocationAndCupName(locationName, cupName, matchType);
    }

    @GetMapping("/match/get_league_matches")
    public List<Match> getLeagueMatches(@RequestParam String leagueName) {
        return matchService.getMatchesByLeagueName(leagueName);
    }

    @GetMapping("/match/create_qualifiers_next_round")
    public List<Match> createQualifiersNextRound(@RequestParam Long cupId, String matchType) {
        return matchService.createQualifiersNextRound(cupId, matchType).get(0);
    }

    @GetMapping("/match/create_semi_finals")
    public List<Match> createSemiFinals(@RequestParam String locationName, String cupName, String matchType) {
        if (locationName == null || cupName == null) return null;
        return matchService.createCupSemifinals(locationName, cupName, matchType);
    }

    @GetMapping("/match/getGroupStat")
    public List<TeamStat> getTeamStat(@RequestParam String locationName, String cupName, String leagueName, String group) {
        return matchService.getTeamStat(locationName, cupName, leagueName, group);
    }

    /*
        Update
     */

    @PostMapping("/match/update_score")
    public void updateScore(@RequestBody Match match) {
        matchService.updateScore(match);
    }

    @PostMapping("match/update_finished")
    public void updateFinished(@RequestBody Match match) {
        matchService.setFinished(match);
    }

    @PostMapping("/match/update_card")
    public void updateCard(@RequestBody CardDetails cardDetails) {
        matchService.updateCard(cardDetails);
    }
}
