package com.isa.services.repository;

import com.isa.services.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShipRepository extends JpaRepository<Ship, Long> {

    @Query(value = "select * from ships s left join fetch services sr where id=?1", nativeQuery = true)
    Ship getByShipID(Long id);
    Ship getShipById(Long id);
    Ship getByNameIgnoreCase(String name);
}
