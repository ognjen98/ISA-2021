package com.isa.requests.repository;

import com.isa.requests.DeleteRequest;
import com.isa.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteRequestRepository extends JpaRepository<DeleteRequest, Long> {

    DeleteRequest findByUser(User u);
}
