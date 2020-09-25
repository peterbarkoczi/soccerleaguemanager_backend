package com.barkoczi.peter.soccerleaguemanager.service;

import com.barkoczi.peter.soccerleaguemanager.entity.Player;
import com.barkoczi.peter.soccerleaguemanager.entity.Team;
import com.barkoczi.peter.soccerleaguemanager.repository.PlayerRepository;
import com.barkoczi.peter.soccerleaguemanager.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class PlayerService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Player addPlayer(String locationName, String teamName, Player player) {

        List<Team> teams = new ArrayList<>();
        teams.add(teamRepository.findTeamByLocationNameAndName(locationName, teamName));
        Player newPlayer = Player.builder()
                .number(player.getNumber())
                .name(player.getName())
                .teams(teams)
                .birthDate(player.getBirthDate())
                .goals(0L)
                .build();

        playerRepository.saveAndFlush(newPlayer);

        return newPlayer;
    }

    public List<Player> getPlayersByTeamId(Long teamId) {
        return playerRepository.findPlayersByTeams(teamRepository.findFirstById(teamId));
    }

    public List<Player> getPlayersByTeamName(String teamName) {
        return playerRepository.findPlayersByTeams(teamRepository.findByName(teamName));
    }
}
