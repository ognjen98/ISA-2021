package com.isa.users.repository;

import com.isa.users.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long> {

    SystemAdmin findByEmail(String email);

    boolean existsSystemAdminByEmail(String email);
}
