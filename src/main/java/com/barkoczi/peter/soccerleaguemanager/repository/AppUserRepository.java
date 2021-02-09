package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.AppUser;
import com.barkoczi.peter.soccerleaguemanager.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    List<AppUser> findAllByLocationOrderByIdAsc(Location location);

    List<AppUser> findAllByOrderByIdAsc();

    Optional<AppUser> findByUsername(String username);

    AppUser findAppUserByUsername(String username);

    boolean existsAppUsersByUsername(String username);

    boolean existsAppUserByUsernameAndLocation(String username, Location location);

    @Modifying(clearAutomatically = true)
    @Query("update AppUser set username = :username, role = :role, teamId = :teamId where id = :id")
    void updateUserDetails(@Param("username") String username,
                           @Param("role") String role,
                           @Param("teamId") Long teamId,
                           @Param("id") Long id);

}
