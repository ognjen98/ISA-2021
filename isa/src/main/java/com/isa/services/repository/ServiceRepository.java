package com.isa.services.repository;

import com.isa.services.Service;
import com.isa.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service getServiceById(Long id);

    List<Service> getServicesBySellerId(Long id);
}
