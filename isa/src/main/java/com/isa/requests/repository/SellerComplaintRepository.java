package com.isa.requests.repository;

import com.isa.requests.SellerComplaint;
import com.isa.users.Client;
import com.isa.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerComplaintRepository extends JpaRepository<SellerComplaint, Long> {

    SellerComplaint getSellerComplaintByClientAndSeller(Client client, Seller seller);
}
