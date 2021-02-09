package com.barkoczi.peter.soccerleaguemanager.controller;

import com.barkoczi.peter.soccerleaguemanager.entity.Location;
import com.barkoczi.peter.soccerleaguemanager.model.location.LocationContact;
import com.barkoczi.peter.soccerleaguemanager.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/list")
    public List<Location> leagueList() {
        return locationService.getAllLocation();
    }

    @GetMapping("/get_location_contact")
    public LocationContact getLocationContactByName(@RequestParam String locationName) {
        return locationService.getLocationContactByName(locationName);
    }

    @PostMapping("/add_location")
    public Location addNewLocation(@RequestBody Location location) {
        return locationService.createAndSaveNewLocation(location);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteLocation(@PathVariable("id") Long id) {
        locationService.deleteLocation(id);
        return "Location deleted";
    }

    @PatchMapping("/edit_contact")
    public String editContacts(@RequestBody LocationContact contact) {
        locationService.editContact(contact);
        return "Contact edited successfully";
    }

}
