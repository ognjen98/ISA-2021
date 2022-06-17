package com.isa.users.repository;

import com.isa.users.Client;
import com.isa.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
