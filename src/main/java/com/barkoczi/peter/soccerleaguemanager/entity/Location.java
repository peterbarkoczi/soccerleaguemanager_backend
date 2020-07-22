package com.barkoczi.peter.soccerleaguemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "location")
    private List<League> leagues = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "location")
    private Set<Team> teams;

}
