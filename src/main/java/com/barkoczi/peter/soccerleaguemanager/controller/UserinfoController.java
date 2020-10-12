package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserinfoController {

    @GetMapping("/me")
    public String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        return user.getUsername() + "\n" + user.getRoles();
    }

}
