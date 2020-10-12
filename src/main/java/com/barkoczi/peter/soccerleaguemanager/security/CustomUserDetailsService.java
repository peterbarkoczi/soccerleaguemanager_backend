package com.barkoczi.peter.soccerleaguemanager.security;

import com.barkoczi.peter.soccerleaguemanager.entity.AppUser;
import com.barkoczi.peter.soccerleaguemanager.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository users;

    public CustomUserDetailsService(UserRepository users) {
        this.users = users;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = users.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        return new User(appUser.getUsername(), appUser.getPassword(),
                appUser.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
