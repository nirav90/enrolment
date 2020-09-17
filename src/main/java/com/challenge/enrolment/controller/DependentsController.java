package com.challenge.enrolment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.enrolment.domain.Dependents;
import com.challenge.enrolment.domain.Enrollee;
import com.challenge.enrolment.exception.ResourceNotFoundException;
import com.challenge.enrolment.repository.EnrolmentRepository;
import com.challenge.enrolment.service.DependentService;
import com.challenge.enrolment.service.EnrolmentService;

@RestController
@RequestMapping("/dependents")
public class DependentsController {

    @Autowired
    private DependentService dependentService;

    @Autowired
    private EnrolmentService enrolmentService;

    
    // Find
    @GetMapping("/dependents")
    List<Dependents> findAll() {
        return dependentService.findAll();
    }

    // Save
    @PostMapping("/add/{enrolleeId}/dependents")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Dependents newDependents(@PathVariable (value = "enrolleeId") Long enrolleeId,@RequestBody Dependents dependents) {
        
		return enrolmentService.findOne(enrolleeId).map(enrollee -> {
			dependents.setEnrollee(enrollee);
			return dependentService.newDependents(dependents);
		}).orElseThrow(() -> new ResourceNotFoundException("enrolleeId " + enrolleeId + " not found")); 
        
    }

    // Find
    @GetMapping("/dependents/{dependentsId}")
    Optional<Dependents> findOne(@PathVariable Long dependentsId) {
        return dependentService.findOne(dependentsId);
    }

    // Save or update
    @PutMapping("/update/dependents/{id}")
    Dependents saveOrUpdate(@RequestBody Dependents dependents, @PathVariable Long id) {
        return dependentService.saveOrUpdate(dependents,id);
    }

    

    @DeleteMapping("/delete/dependents/{id}")
    void deleteDependents(@PathVariable Long id) {
    	dependentService.deleteDependents(id);
    }

}