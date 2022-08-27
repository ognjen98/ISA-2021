package com.isa.revisions.repository;

import com.isa.revisions.Revision;
import com.isa.services.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevisionRepository extends JpaRepository<Revision, Long> {


}
