package com.barkoczi.peter.soccerleaguemanager.service;

import com.barkoczi.peter.soccerleaguemanager.entity.Cup;
import com.barkoczi.peter.soccerleaguemanager.entity.Match;
import com.barkoczi.peter.soccerleaguemanager.repository.MatchRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> createMatches(List<String> teamsList, Cup cup, String startTime, String matchTime) {

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        List<String> temp;
        Calendar time = setTime(startTime);
        List<Match> matches = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            temp = getRandomElement(teamsList);
            Match newMatch = Match.builder()
                    .cup(cup)
                    .time(df.format(time.getTime()))
                    .team1(temp.get(0))
                    .team2(temp.get(1))
                    .score1(0)
                    .score2(0)
                    .scorer1("")
                    .scorer2("")
                    .card1("")
                    .card2("")
                    .isFinished(false)
                    .build();
            matches.add(newMatch);
            deleteTeams(teamsList, temp);
            time.add(Calendar.MINUTE, Integer.parseInt(matchTime) + 5);
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

    private Calendar setTime(String startTime) {

        String[] splittedTime = startTime.split(":");
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR, Integer.parseInt(splittedTime[0]));
        time.set(Calendar.MINUTE, Integer.parseInt(splittedTime[1]));

        return time;
    }

    @Transactional
    public void updateScore(Match match) {
        String scorer;
        if (match.getScore1() != null) {
            scorer = matchRepository.findFirstById(match.getId()).getScorer1();
            scorer += match.getScorer1() + "\r\n";
            matchRepository.updateScore1(match.getScore1(), scorer, match.getId());
        } else if (match.getScore2() != null){
            scorer = matchRepository.findFirstById(match.getId()).getScorer2();
            scorer += match.getScorer2() + "\r\n";
            matchRepository.updateScore2(match.getScore2(), scorer, match.getId());
        }
    }

}
