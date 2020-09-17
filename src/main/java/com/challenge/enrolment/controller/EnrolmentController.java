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

import com.challenge.enrolment.domain.Enrollee;
import com.challenge.enrolment.service.EnrolmentService;

@RestController
@RequestMapping("/enrolment")
public class EnrolmentController {

    @Autowired
    private EnrolmentService enrolmentService;

    // Find
    @GetMapping("/enrollees")
    List<Enrollee> findAll() {
        return enrolmentService.findAll();
    }

    // Save
    @PostMapping("/add/enrollee")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Enrollee newBook(@RequestBody Enrollee enrollee) {
        return enrolmentService.newEnrollee(enrollee);
    }

    // Find
    @GetMapping("/enrollee/{id}")
    Optional<Enrollee> findOne(@PathVariable Long id) {
        return enrolmentService.findOne(id);
    }

    // Save or update
    @PutMapping("/update/enrollee/{id}")
    Enrollee saveOrUpdate(@RequestBody Enrollee enrollee, @PathVariable Long id) {
        return enrolmentService.saveOrUpdate(enrollee,id);
    }

    

    @DeleteMapping("/delete/enrollee/{id}")
    void deleteBook(@PathVariable Long id) {
    	enrolmentService.deleteEnrollee(id);
    }

}