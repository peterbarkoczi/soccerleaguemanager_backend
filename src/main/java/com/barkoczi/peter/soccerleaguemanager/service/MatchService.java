package com.barkoczi.peter.soccerleaguemanager.service;

import com.barkoczi.peter.soccerleaguemanager.entity.Cup;
import com.barkoczi.peter.soccerleaguemanager.entity.Match;
import com.barkoczi.peter.soccerleaguemanager.repository.MatchRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> createMatches(List<String> teamsList, Cup cup) {

        List<String> temp;
        String time;
        List<Match> matches = new ArrayList<>();

        for (int i = 0, j = 10; i < 4; i++, j++) {
            temp = getRandomElement(teamsList);
            time = j + ":00";
            Match newMatch = Match.builder()
                    .cup(cup)
                    .time(time)
                    .team1(temp.get(0))
                    .team2(temp.get(1))
                    .build();
            matches.add(newMatch);
            deleteTeams(teamsList, temp);
        }
        for (Match match : matches) {
            matchRepository.saveAndFlush(match);
        }
        return matches;
    }

    private List<String> getRandomElement(List<String> list) {

        Random random = new Random();
        List<String> teams = new ArrayList<>();

        while (teams.size() < 2) {
            int rand = random.nextInt(list.size());
            if (!teams.contains(list.get(rand))) {
                teams.add(list.get(rand));
            }
        }

        return teams;
    }

    private void deleteTeams(List<String> list, List<String> temp) {
        for (String item : temp) {
            list.remove(item);
        }
    }

}
