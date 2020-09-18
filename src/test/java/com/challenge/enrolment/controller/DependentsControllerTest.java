package com.challenge.enrolment.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.challenge.enrolment.domain.Dependents;
import com.challenge.enrolment.domain.Enrollee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DependentsControllerTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get_all_dependents_with_status200() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/dependents/dependents").toString(), String.class);
        assertEquals(200, response.getStatusCodeValue());

    }
    
    @Test
    public void get_all_dependents_with_status_404() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/1q/dependents/dependents").toString(), String.class);
        assertEquals(404, response.getStatusCodeValue());

    }
    
    @Test
    public void get_dependents_by_id_with_status_200() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/dependents/dependents/1").toString(), String.class);
        assertEquals(200, response.getStatusCodeValue());

    }
    
    
    @Test
    public void add_dependents_with_status_200() throws URISyntaxException 
    {
    	
    	Enrollee enrollee = addEnrollee();
    	
        final String baseUrl = "http://localhost:"+port+"/dependents/add/"+enrollee.getEnrolleeId()+"/dependents";
        URI uri = new URI(baseUrl);
        Dependents dependents = new Dependents();
        dependents.setName("test");
        dependents.setBirthdate(new Date(1600387200000l));
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Dependents> request = new HttpEntity<>(dependents, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        assertEquals(201, result.getStatusCodeValue());
    }
      
    @Test
    public void add_dependents_with_status_404() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+port+"/dependents/add/dependents";
        URI uri = new URI(baseUrl);
        Enrollee enrollee = null;
         
        HttpHeaders headers = null;
       
        HttpEntity<Enrollee> request = new HttpEntity<>(enrollee, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    public void update_enrollee_with_status_200() throws URISyntaxException, RestClientException, MalformedURLException 
    {
    	
    	Enrollee enrollee = addEnrollee();
    	Dependents dependents = addDependents(enrollee.getEnrolleeId());
    	
    	ResponseEntity<Dependents> dependentsEntitiy = restTemplate.getForEntity(new URL("http://localhost:" + port + "/dependents/dependents/"+dependents.getDependentId()).toString(), Dependents.class);
    	Dependents dependentsToUpdate = dependentsEntitiy.getBody();
    	
    	dependentsToUpdate.setName("admin1test");
        
    	restTemplate.put(new URL("http://localhost:" + port + "/dependents/update/dependents/"+dependents.getDependentId()).toString(), enrollee);
        
    	ResponseEntity<Dependents> dependentsEntitiy2 = restTemplate.getForEntity(new URL("http://localhost:" + port + "/dependents/dependents/"+dependents.getDependentId()).toString(), Dependents.class);
    	Dependents dependentsUpdated = dependentsEntitiy2.getBody();
    	
    	
    	assertNotNull(dependentsUpdated);
        
    }
    
    
    @Test
    public void delete_enrollee_with_status_200() throws URISyntaxException, RestClientException, MalformedURLException 
    {
    	
    	Enrollee enrollee = addEnrollee();
    	Dependents dependents = addDependents(enrollee.getEnrolleeId());
    	
    	ResponseEntity<Dependents> dependentsEntitiy = restTemplate.getForEntity(new URL("http://localhost:" + port + "/dependents/dependents/"+dependents.getDependentId()).toString(), Dependents.class);
    	Dependents dependentsToDelete = dependentsEntitiy.getBody();
    	       
    	restTemplate.delete(new URL("http://localhost:" + port + "/dependents/delete/dependents/"+dependentsToDelete.getDependentId()).toString(), enrollee);
    	
    	try {
    		ResponseEntity<Dependents> dependents2Entitiy = restTemplate.getForEntity(new URL("http://localhost:" + port + "/dependents/dependents/"+dependents.getDependentId()).toString(), Dependents.class);
        	Dependents dependentsAfterDelete = dependents2Entitiy.getBody();
        	
       } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
       }
    	
    }
    
    private Enrollee addEnrollee() throws URISyntaxException {
    	final String baseUrl = "http://localhost:"+port+"/enrolment/add/enrollee";
        URI uri = new URI(baseUrl);
        Enrollee enrollee = new Enrollee();
        enrollee.setName("test");
        enrollee.setPhoneNo("98989765678");
        enrollee.setStatus(true);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
        HttpEntity<Enrollee> request = new HttpEntity<>(enrollee, headers);
        ResponseEntity<Enrollee> result = this.restTemplate.postForEntity(uri, request, Enrollee.class);
        return result.getBody();
    }
    
    private Dependents addDependents(long enrolleeId) throws URISyntaxException {

    	final String baseUrl = "http://localhost:"+port+"/dependents/add/"+enrolleeId+"/dependents";
        URI uri = new URI(baseUrl);
        Dependents dependents = new Dependents();
        dependents.setName("test");
        dependents.setBirthdate(new Date(1600387200000l));
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
        HttpEntity<Dependents> request = new HttpEntity<>(dependents, headers);
        ResponseEntity<Dependents> result = this.restTemplate.postForEntity(uri, request, Dependents.class);
        return result.getBody();
    }
    
    

}