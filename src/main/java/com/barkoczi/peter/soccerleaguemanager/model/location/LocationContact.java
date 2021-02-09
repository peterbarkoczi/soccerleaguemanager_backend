package com.barkoczi.peter.soccerleaguemanager.model.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class LocationContact {

    private Long locationId;
    private String contactName;
    private String contactPhone;
    private String contactMail;
    private String address;

}
