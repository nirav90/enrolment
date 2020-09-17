package com.challenge.enrolment.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.enrolment.domain.Dependents;

@Repository
public interface DependentsRepository extends JpaRepository<Dependents, Long> {
	
	Page<Dependents> findByEnrollee(Long enrollee, Pageable pageable);
    Optional<Dependents> findByDependentIdAndEnrollee(Long dependentId, Long enrolleeId);
}
