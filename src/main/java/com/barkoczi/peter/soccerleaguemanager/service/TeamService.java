package com.barkoczi.peter.soccerleaguemanager.service;

import com.barkoczi.peter.soccerleaguemanager.entity.League;
import com.barkoczi.peter.soccerleaguemanager.entity.Team;
import com.barkoczi.peter.soccerleaguemanager.model.TeamDetails;
import com.barkoczi.peter.soccerleaguemanager.repository.LeagueRepository;
import com.barkoczi.peter.soccerleaguemanager.repository.LocationRepository;
import com.barkoczi.peter.soccerleaguemanager.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    TeamDetails teamDetails;

    public Team addTeam(TeamDetails teamDetails) {
        return createAndSaveNewTeam(teamDetails);
    }

    private Team createAndSaveNewTeam(TeamDetails teamDetails) {
//        League league = leagueRepository.findFirstByName(teamDetails.getLeagueName());
//        List<League> leagues = new ArrayList<>();
//        leagues.add(league);
        Team newTeam = Team.builder()
                .name(teamDetails.getTeamName())
                .location(locationRepository.findFirstById(teamDetails.getLocationId()))
                .league(new ArrayList<>())
                .build();

        teamRepository.saveAndFlush(newTeam);
        return newTeam;
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public List<Team> getTeamsByLeague(Long leagueId) {
        return teamRepository.findTeamsByLeagueOrderByNameAsc(leagueRepository.findFirstById(leagueId));
    }
}
