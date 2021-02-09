package com.barkoczi.peter.soccerleaguemanager.repository;

import com.barkoczi.peter.soccerleaguemanager.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByOrderById();

    Location findFirstById(Long id);

    Location findLocationByName(String name);

    @Query("update Location set address = :address, contactName = :contactName, " +
            "contactPhone = :contactPhone, contactMail = :contactMail where id = :id")
    @Modifying(clearAutomatically = true)
    void updateContact(@Param("address") String address,
                       @Param("contactName") String contactName,
                       @Param("contactPhone") String contactPhone,
                       @Param("contactMail") String contactMail,
                       @Param("id") Long id);
}
