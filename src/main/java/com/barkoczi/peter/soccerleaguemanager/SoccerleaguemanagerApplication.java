package com.barkoczi.peter.soccerleaguemanager;

import com.barkoczi.peter.soccerleaguemanager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoccerleaguemanagerApplication {

    @Autowired
    private TeamRepository teamRepository;

    public static void main(String[] args) {
        SpringApplication.run(SoccerleaguemanagerApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init() {
//        return args -> {
//            Team team1 = Team.builder()
//                    .leagueId(1)
//                    .name("Kiss Team")
//                    .subLeague("Kedd")
//                    .build();
//
//            teamRepository.save(team1);
//        };
//    }

}
