package com.barkoczi.peter.soccerleaguemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String time;
    private String team1;
    private String team2;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cup cup;

}
