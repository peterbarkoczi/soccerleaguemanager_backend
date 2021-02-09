package com.barkoczi.peter.soccerleaguemanager.service;

import com.barkoczi.peter.soccerleaguemanager.entity.AppUser;
import com.barkoczi.peter.soccerleaguemanager.exception.UserNotFoundException;
import com.barkoczi.peter.soccerleaguemanager.model.user.SignupCredentials;
import com.barkoczi.peter.soccerleaguemanager.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class AppUserService {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private LocationService locationService;

    public boolean isUserExists(String username, String locationName) {
        return appUserRepository.existsAppUsersByUsername(username);
//        return appUserRepository.existsAppUserByUsernameAndLocation(username, locationService.getLocationByName(locationName));
    }

    public void createAndSaveNewAppUser(SignupCredentials credentials) {
        AppUser tempUser = AppUser.builder()
                .username(credentials.getUsername())
                .password(passwordEncoder.encode(credentials.getPassword()))
                .role(credentials.getRole())
                .location(locationService.getLocationRepository().findLocationByName(credentials.getLocationName()))
                .build();

        appUserRepository.saveAndFlush(tempUser);
    }

    public List<AppUser> getAllUserByLocation(String locationName) {
        return appUserRepository.findAllByOrderByIdAsc();
//        return appUserRepository.findAllByLocationOrderByIdAsc(locationService.getLocationByName(locationName));
    }

    public Long getTeamId(String username) {
        Optional<AppUser> user = appUserRepository.findByUsername(username);
        return user.map(AppUser::getTeamId).orElse(null);
    }

    @Transactional
    public void updateUser(AppUser user, Long userId) {
        appUserRepository.updateUserDetails(user.getUsername(), user.getRole(), user.getTeamId(), userId);
    }

    public void deleteUserById(Long userId) {
        appUserRepository.deleteById(userId);
    }

    public String checkLocation(String username, String locationName) throws UserNotFoundException {
        AppUser user = appUserRepository.findAppUserByUsername(username);
        if (user.getLocation().getName().equals(locationName)) return locationName;
        throw new UserNotFoundException("User not found in this location");
    }

    @Transactional
    public void changeRole(SignupCredentials user) {
        appUserRepository.updateRole(user.getRole(), user.getUsername());
    }
}
