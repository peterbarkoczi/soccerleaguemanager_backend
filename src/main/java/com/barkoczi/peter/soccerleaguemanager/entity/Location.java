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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(unique = true)
    private String name;

    private String contactName;
    private String contactPhone;
    private String contactMail;
    private String address;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "location", orphanRemoval = true)
    private List<League> leagues = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "location", orphanRemoval = true)
    private Set<Cup> cups;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "location", orphanRemoval = true)
    private Set<Team> teams;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "location", orphanRemoval = true)
    private List<News> news = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "locations")
    private List<AppUser> users;

    @PreRemove
    private void removeUserFromLocations() {
        for (AppUser user : users) {
            user.getLocations().remove(this);
        }
    }

}
