package com.isa.requests.repository;

import com.isa.requests.SellerComplaint;
import com.isa.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerComplaintRepository extends JpaRepository<SellerComplaint, Long> {

    SellerComplaint getSellerComplaintByClient(Client client);
}
