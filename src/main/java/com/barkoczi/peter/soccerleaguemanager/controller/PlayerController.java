package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.Player;
import com.barkoczi.peter.soccerleaguemanager.model.PlayerDetails;
import com.barkoczi.peter.soccerleaguemanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;


    @GetMapping("/player/list/{teamId}")
    public List<Player> getPlayersById(@PathVariable("teamId") Long teamId) {
        return playerService.getPlayersByTeamId(teamId);
    }

    @GetMapping("/player/listByName/{teamName}")
    public List<Player> getPlayersByName(@PathVariable("teamName") String teamName) {
        return playerService.getPlayersByTeamName(teamName);
    }

    @GetMapping("/player/get_player_details")
    public PlayerDetails getPlayerDetails(@RequestParam("playerId") Long playerId) {
        return playerService.getPlayerDetails(playerId);
    }

    @PostMapping("/player/add_player/{locationName}-{teamName}")
    public Player addPlayer(@PathVariable("locationName") String locationName,
                            @PathVariable("teamName") String teamName,
                            @RequestBody Player player) {
        return playerService.addPlayer(locationName, teamName, player);
    }

    @DeleteMapping(value = "/player/{playerId}")
    public String deletePlayer(@PathVariable("playerId") Long playerId) {
        playerService.deletePlayer(playerId);
        return "Player deleted";
    }
}
