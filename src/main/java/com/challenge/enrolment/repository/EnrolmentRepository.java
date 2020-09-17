package com.challenge.enrolment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.enrolment.domain.Enrollee;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrollee, Long> {
}
