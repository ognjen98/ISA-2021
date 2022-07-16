package com.isa.users.repository;

import com.isa.services.Reservation;
import com.isa.services.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation getReservationById(Long id);
}
