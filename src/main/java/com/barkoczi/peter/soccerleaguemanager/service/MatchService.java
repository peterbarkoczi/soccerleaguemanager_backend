package com.barkoczi.peter.soccerleaguemanager.service;

import com.barkoczi.peter.soccerleaguemanager.entity.Cup;
import com.barkoczi.peter.soccerleaguemanager.entity.Match;
import com.barkoczi.peter.soccerleaguemanager.entity.Team;
import com.barkoczi.peter.soccerleaguemanager.model.CardDetails;
import com.barkoczi.peter.soccerleaguemanager.repository.CupRepository;
import com.barkoczi.peter.soccerleaguemanager.repository.MatchRepository;
import com.barkoczi.peter.soccerleaguemanager.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private CupRepository cupRepository;

    @Autowired
    private TeamRepository teamRepository;


    public List<Match> createQualifierMatches(List<String> teamsList, Cup cup, String startTime, String matchTime, String matchType) {
        return createMatches(teamsList, cup, startTime, matchTime, matchType, false);
    }

    public List<Match> createQualifiersNextRound(Long cupId, String matchType) {
        if (!matchRepository.existsMatchByCupIdAndMatchType(cupId, setMatchType(matchType))) {
            System.out.println("create new round");
            List<String> teams = createPairs(matchType, matchRepository.findAllByCupIdAndMatchType(cupId, matchType));
            Cup cup = cupRepository.findCupById(cupId);
            String matchTime = cup.getMatchTime();
            String startTime = matchRepository.getMaxTime(cupId);

            return createMatches(teams, cup, startTime, matchTime, setMatchType(matchType), true);
        }
        return null;
    }

    public List<Match> createSemiFinals(Long cupId, String matchType) {
        if (matchRepository.findAllByCupIdAndMatchType(cupId, matchType).isEmpty()) {
            List<Match> matches = getMatches(cupId, matchType);
            Cup cup = cupRepository.findCupById(cupId);
            if (matches.size() < setMatchNumber(matchType)) {
                System.out.println("Not finished all match");
                return null;
            } else {
                String startTime = matchRepository.getMaxTime(cupId);
                List<String> teams = createPairs(matchType, matches);
                return createMatches(teams, cup, startTime, cup.getMatchTime(), matchType, false);
            }
        }
        return null;
    }

    private String setMatchType(String matchType) {
        return switch (matchType) {
            case "qualifier-1/16" -> "qualifier-1/8";
            case "qualifier-1/8" -> "qualifier-1/4";
            default -> null;
        };
    }

    private List<Match> createMatches(List<String> teamsList, Cup cup, String startTime, String matchTime, String matchType, boolean isQualifierNextRound) {

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        List<String> temp;
        Calendar time = setTime(startTime);
        List<Match> matches = new ArrayList<>();
        int range = teamsList.size() / 2;

        if (matchType.equals("semiFinal") || matchType.equals("final") || isQualifierNextRound) {
            time.add(Calendar.MINUTE, Integer.parseInt(matchTime) + 10);
        }

        for (int i = 0; i < range; i++) {
            if (matchType.contains("qualifier")) {
                temp = getRandomTeams(teamsList);
            } else {
                temp = getFirstTwoTeams(teamsList);
            }
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
                    .finished(false)
                    .matchType(matchType)
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

    private List<String> getRandomTeams(List<String> list) {

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

    private List<String> getFirstTwoTeams(List<String> teamList) {
        List<String> teams = new ArrayList<>();

        teams.add(teamList.get(0));
        teams.add(teamList.get(1));

        return teams;
    }

    private void deleteTeams(List<String> list, List<String> temp) {
        for (String item : temp) {
            list.remove(item);
        }
    }

    private Calendar setTime(String startTime) {
        String[] splitTime = startTime.split(":");
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitTime[0].replaceAll("\\s+", "")));
        time.set(Calendar.MINUTE, Integer.parseInt(splitTime[1].replaceAll("\\s+", "")));
        return time;
    }

    private List<String> createPairs(String matchType, List<Match> matches) {
        List<String> teams;
        List<String> winners = new ArrayList<>();
        List<String> losers = new ArrayList<>();
        for (Match match : matches) {
            if (match.getScore1() > match.getScore2()) {
                winners.add(match.getTeam1());
                losers.add(match.getTeam2());
            } else {
                winners.add(match.getTeam2());
                losers.add(match.getTeam1());

            }
        }
        teams = getTeams(matchType, winners, losers);
        return teams;
    }

    private List<String> getTeams(String matchType, List<String> winners, List<String> losers) {
        if (matchType.equals("final")) {
            return Stream.concat(losers.stream(), winners.stream())
                    .collect(Collectors.toList());
        } else {
            return winners;
        }
    }

    private int setMatchNumber(String matchType) {
        if (matchType.equals("final")) {
            return 2;
        }
        return 4;
    }

    private List<Match> getMatches(Long cupId, String matchType) {
        if (matchType.equals("semiFinal")) {
            return matchRepository.findMatchesByFinishedEqualsAndCupIdAndMatchTypeContains(true, cupId, "qualifier-1/4");
        } else {
            return matchRepository.findMatchesByFinishedEqualsAndCupIdAndMatchType(true, cupId, "semiFinal");
        }
    }

    /* Update methods */

    @Transactional
    public void updateScore(Match match) {
        String scorer;
        if (match.getScore1() != null) {
            scorer = matchRepository.findFirstById(match.getId()).getScorer1();
            scorer += match.getScorer1() + "\r\n";
            matchRepository.updateScore1(match.getScore1(), scorer, match.getId());
        } else if (match.getScore2() != null) {
            scorer = matchRepository.findFirstById(match.getId()).getScorer2();
            scorer += match.getScorer2() + "\r\n";
            matchRepository.updateScore2(match.getScore2(), scorer, match.getId());
        }
    }

    @Transactional
    public void setFinished(Match match) {
        matchRepository.updateFinished(match.getId());
    }

    @Transactional
    public void updateCard(CardDetails cardDetails) {
        String player;
        if (!cardDetails.getCard1().equals("")) {
            player = matchRepository.findFirstById(cardDetails.getId()).getCard1();
            player += cardDetails.getType() + " - " + cardDetails.getCard1() + "\r\n";
            matchRepository.updateCard1(cardDetails.getId(), player);
        } else if (!cardDetails.getCard2().equals("")) {
            player = matchRepository.findFirstById(cardDetails.getId()).getCard2();
            player += cardDetails.getType() + " - " + cardDetails.getCard2() + "\r\n";
            matchRepository.updateCard2(cardDetails.getId(), player);
        }
    }
}
