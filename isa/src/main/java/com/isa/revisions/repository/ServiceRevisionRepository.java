package com.isa.revisions.repository;

import com.isa.revisions.Revision;
import com.isa.revisions.ServiceRevision;
import com.isa.services.Service;
import com.isa.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRevisionRepository extends JpaRepository<ServiceRevision, Long> {
    List<ServiceRevision> getServiceRevisionsByService(Service service);

    ServiceRevision getServiceRevisionByClient(Client client);

}
