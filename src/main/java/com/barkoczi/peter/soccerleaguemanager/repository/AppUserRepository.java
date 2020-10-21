package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

}
