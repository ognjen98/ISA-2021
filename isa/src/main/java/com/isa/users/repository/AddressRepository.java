package com.isa.users.repository;

import com.isa.users.Address;
import com.isa.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
