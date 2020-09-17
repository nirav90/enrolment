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

import com.challenge.enrolment.domain.Dependents;
import com.challenge.enrolment.domain.Enrollee;
import com.challenge.enrolment.repository.DependentsRepository;
import com.challenge.enrolment.repository.EnrolmentRepository;

@Service
public class DependentService {

	@Autowired
    private DependentsRepository repository;

    // Find
    public List<Dependents> findAll() {
        return repository.findAll();
    }

    // Save
    public Dependents newDependents( Dependents dependents) {
        return repository.save(dependents);
    }

    // Find
    public Optional<Dependents> findOne( Long id) {
        return repository.findById(id);
    }

    // Save or update
    public Dependents saveOrUpdate(  Dependents dependents,  Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(dependents.getName());
                    x.setBirthdate(dependents.getBirthdate());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                	dependents.setDependentId(id);
                    return repository.save(dependents);
                });
    }

    

    public void deleteDependents(Long id) {
        repository.deleteById(id);
    }

}