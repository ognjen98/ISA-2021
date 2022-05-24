package com.isa.users.repository;

import com.isa.users.Client;
import com.isa.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);
    boolean existsClientByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Client a SET a.enabled=true WHERE a.email=?1")
    int enableAppUser(String email);
}
