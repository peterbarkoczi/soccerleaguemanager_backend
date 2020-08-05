package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MatchRepository extends JpaRepository<Match, Long> {

    Match findFirstById(Long id);

    List<Match> findMatchesByFinishedEqualsAndCupIdAndMatchType(boolean finished, Long cupId, String matchType);

    List<Match> findAllByCupIdAndMatchType(Long cupId, String matchType);

    @Query("update Match set score1 = :score, scorer1 = :scorer where id = :id")
    @Modifying(clearAutomatically = true)
    void updateScore1(@Param("score") int score, @Param("scorer") String scorer, @Param("id") Long id);

    @Query("update Match set score2 = :score, scorer2 = :scorer where id = :id")
    @Modifying(clearAutomatically = true)
    void updateScore2(@Param("score") int score, @Param("scorer") String scorer, @Param("id") Long id);

    @Query("update Match set finished = true where id = :id")
    @Modifying(clearAutomatically = true)
    void updateFinished(@Param("id") Long id);

    @Query("select max(time) from Match where finished=true and cup.id = :cupId")
    String getMaxTime(@Param("cupId") Long cupId);
}
