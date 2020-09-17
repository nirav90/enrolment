package com.challenge.enrolment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.enrolment.domain.Enrollee;
import com.challenge.enrolment.repository.EnrolmentRepository;

@Service
public class EnrolmentService {

    @Autowired
    private EnrolmentRepository repository;

    // Find
    public List<Enrollee> findAll() {
        return repository.findAll();
    }

    // Save
    public Enrollee newEnrollee( Enrollee enrollee) {
        return repository.save(enrollee);
    }

    // Find
    public Optional<Enrollee> findOne( Long id) {
        return repository.findById(id);
    }

    // Save or update
    public Enrollee saveOrUpdate( Enrollee enrollee,  Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(enrollee.getName());
                    x.setPhoneNo(enrollee.getPhoneNo());
                    x.setStatus(enrollee.isStatus());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                	enrollee.setEnrolleeId(id);
                    return repository.save(enrollee);
                });
    }

    

    public void deleteEnrollee(Long id) {
        repository.deleteById(id);
    }

}