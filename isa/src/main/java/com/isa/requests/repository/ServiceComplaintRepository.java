package com.isa.requests.repository;

import com.isa.requests.ServiceComplaint;
import com.isa.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceComplaintRepository extends JpaRepository<ServiceComplaint, Long> {

    ServiceComplaint getServiceComplaintByClient(Client client);
}
