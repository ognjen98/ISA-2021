package com.isa.users.repository;

import com.isa.services.Reservation;
import com.isa.services.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation getReservationById(Long id);

    @Transactional
    @Modifying
    @Query(value = "update reservations set cancelled = ?2, reserved = ?3 where id = ?1", nativeQuery = true)
    void update(Long id, Boolean cancelled, Boolean reserved);
}
