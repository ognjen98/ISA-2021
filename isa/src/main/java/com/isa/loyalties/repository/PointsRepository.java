package com.isa.loyalties.repository;

import com.isa.loyalties.Points;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface PointsRepository extends JpaRepository<Points, Long> {
}
