package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.Team;
import com.barkoczi.peter.soccerleaguemanager.model.TeamDetails;
import com.barkoczi.peter.soccerleaguemanager.repository.TeamRepository;
import com.barkoczi.peter.soccerleaguemanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @GetMapping("/teams")
    public List<Team> getLocationTeams(@RequestParam() String locationName) {
        return teamRepository.findTeamsByLocationNameOrderByNameAsc(locationName);
    }

    @GetMapping("/teams/{leagueId}")
    public List<Team> getTeamsByLeagueId(@PathVariable("leagueId") Long leagueId) {
        return teamService.getTeamsByLeague(leagueId);
    }

    @PostMapping("/teams/add_team")
    public Team addNewTeam(@RequestBody TeamDetails teamDetails) {
        return teamService.addTeam(teamDetails);
    }

    @DeleteMapping(value = "/teams/{id}")
    public String deleteCup(@PathVariable("id") Long id) {
        teamService.deleteTeam(id);
        return "Team deleted";
    }
}
