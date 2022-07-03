package com.isa.services.repository;

import com.isa.services.TimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;

public interface TimePeriodRepository extends JpaRepository<TimePeriod, Long> {
}
