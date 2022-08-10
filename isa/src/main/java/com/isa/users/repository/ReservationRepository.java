package com.isa.users.repository;

import com.isa.services.Reservation;
import com.isa.services.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation getReservationById(Long id);

    List<Reservation> getReservationsByClientId(Long id);

    List<Reservation> getReservationsByServiceId(Long id);
}
