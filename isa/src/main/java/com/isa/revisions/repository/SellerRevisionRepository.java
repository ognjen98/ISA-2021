package com.isa.revisions.repository;

import com.isa.revisions.SellerRevision;
import com.isa.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRevisionRepository extends JpaRepository<SellerRevision, Long> {

    List<SellerRevision> getSellerRevisionsBySeller(Seller seller);
}
