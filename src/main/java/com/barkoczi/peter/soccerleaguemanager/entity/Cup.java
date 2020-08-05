package com.barkoczi.peter.soccerleaguemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cup")
public class Cup {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String date;

    private String matchTime;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Location location;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cup")
    private List<Match> matches = new ArrayList<>();

}
