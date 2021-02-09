package com.barkoczi.peter.soccerleaguemanager.service;

import com.barkoczi.peter.soccerleaguemanager.entity.Location;
import com.barkoczi.peter.soccerleaguemanager.model.location.LocationContact;
import com.barkoczi.peter.soccerleaguemanager.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location createAndSaveNewLocation(Location location) {
        Location newLocation = Location.builder()
                .name(location.getName())
                .address(location.getAddress())
                .contactName(location.getContactName())
                .contactPhone(location.getContactPhone())
                .contactMail(location.getContactMail())
                .leagues(new ArrayList<>())
                .teams(new HashSet<>())
                .build();

        locationRepository.saveAndFlush(newLocation);

        return newLocation;
    }

    public void deleteLocation(Long id) {
        locationRepository.delete(locationRepository.findFirstById(id));
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAllByOrderById();
    }

    public LocationContact getLocationContactByName(String locationName) {
        Location tempLocation = locationRepository.findLocationByName(locationName);
        return LocationContact.builder()
                .locationId(tempLocation.getId())
                .contactName(tempLocation.getContactName())
                .contactPhone(tempLocation.getContactPhone())
                .contactMail(tempLocation.getContactMail())
                .address(tempLocation.getAddress()).build();
    }

    public Location getLocationByName(String locationName) {
        return locationRepository.findLocationByName(locationName);
    }

    @Transactional
    public void editContact(LocationContact contact) {
        locationRepository.updateContact(
                contact.getAddress(),
                contact.getContactName(),
                contact.getContactPhone(),
                contact.getContactMail(),
                contact.getLocationId());
    }
}
