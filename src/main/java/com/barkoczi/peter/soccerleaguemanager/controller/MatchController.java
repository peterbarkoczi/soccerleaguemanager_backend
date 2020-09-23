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
    public List<Match> getQualifiers(@RequestParam Long cupId, String matchType) {
        return matchRepository.findMatchesByCupIdAndMatchTypeContains(cupId, matchType);
    }

    @GetMapping("/match/get_league_matches")
    public List<Match> getLeagueMatches(@RequestParam Long leagueId) {
        return matchRepository.findMatchesByLeagueId(leagueId);
    }

    @GetMapping("/match/create_qualifiers_next_round")
    public List<Match> createQualifiersNextRound(@RequestParam Long cupId, String matchType) {
        return matchService.createQualifiersNextRound(cupId, matchType).get(0);
    }

    @GetMapping("/match/create_semi_finals")
    public List<Match> createSemiFinals(@RequestParam Long cupId, String matchType) {
        List<List<Match>> result = matchService.createSemiFinals(cupId, matchType);
        if (result != null) {
            System.out.println(result);
            return result.get(0);
        }
        return null;
    }

    @GetMapping("/match/getGroupStat")
    public List<TeamStat> getTeamStat(@RequestParam Long cupId, Long leagueId, String group) {
        return teamStatCreator.createTeamStat(cupId, leagueId, group);
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
