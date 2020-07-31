package com.barkoczi.peter.soccerleaguemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @Column(name = "score1", columnDefinition = "integer default 0")
    private Integer score1 = 0;

    @Column(columnDefinition = "integer default 0")
    private Integer score2 = 0;

    @Column(columnDefinition = "varchar(255) default ''")
    private String scorer1 = "";
    @Column(columnDefinition = "varchar(255) default ''")
    private String scorer2 = "";

    @Column(columnDefinition = "varchar(255) default ''")
    private String card1 = "";
    @Column(columnDefinition = "varchar(255) default ''")
    private String card2 = "";

    @Column(columnDefinition = "boolean default false")
    private boolean isFinished = false;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cup cup;

}
