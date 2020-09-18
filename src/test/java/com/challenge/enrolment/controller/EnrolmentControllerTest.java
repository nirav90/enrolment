package com.challenge.enrolment.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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

import com.challenge.enrolment.domain.Enrollee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EnrolmentControllerTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get_all_enrollee_with_status200() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/enrolment/enrollees").toString(), String.class);
        assertEquals(200, response.getStatusCodeValue());

    }
    
    @Test
    public void get_all_enrollee_with_status_404() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/1/emnrolment/enrollees").toString(), String.class);
        assertEquals(404, response.getStatusCodeValue());

    }
    
    @Test
    public void get_enrollee_by_id_with_status_200() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/enrolment/enrollee/1").toString(), String.class);
        assertEquals(200, response.getStatusCodeValue());

    }
    
    
    @Test
    public void add_enrollee_with_status_200() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+port+"/enrolment/add/enrollee";
        URI uri = new URI(baseUrl);
        Enrollee enrollee = new Enrollee();
        enrollee.setName("test");
        enrollee.setPhoneNo("98989765678");
        enrollee.setStatus(true);
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Enrollee> request = new HttpEntity<>(enrollee, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        assertEquals(201, result.getStatusCodeValue());
    }
      
    @Test
    public void add_enrollee_with_status_415() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+port+"/enrolment/add/enrollee";
        URI uri = new URI(baseUrl);
        Enrollee enrollee = null;
         
        HttpHeaders headers = null;
       
        HttpEntity<Enrollee> request = new HttpEntity<>(enrollee, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        assertEquals(415, result.getStatusCodeValue());
    }

    @Test
    public void update_enrollee_with_status_200() throws URISyntaxException, RestClientException, MalformedURLException 
    {
    	
    	final String baseUrl = "http://localhost:"+port+"/enrolment/add/enrollee";
        URI uri = new URI(baseUrl);
        Enrollee enrolleetoAdd = new Enrollee();
        enrolleetoAdd.setEnrolleeId(100l);
        enrolleetoAdd.setName("test");
        enrolleetoAdd.setPhoneNo("98989765678");
        enrolleetoAdd.setStatus(true);
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Enrollee> request = new HttpEntity<>(enrolleetoAdd, headers);
         
        ResponseEntity<Enrollee> result = this.restTemplate.postForEntity(uri, request, Enrollee.class);
         
        long id = result.getBody().getEnrolleeId();
    	
    	ResponseEntity<Enrollee> enrolleeEntitiy = restTemplate.getForEntity(new URL("http://localhost:" + port + "/enrolment/enrollee/" + id).toString(), Enrollee.class);
    	Enrollee enrollee = enrolleeEntitiy.getBody();
    	
    	enrollee.setName("admin1");
    	enrollee.setPhoneNo("45454545454");
        
    	restTemplate.put(new URL("http://localhost:" + port + "/enrolment/update/enrollee/"+id).toString(), enrollee);
        
    	ResponseEntity<Enrollee> enrolleeEntitiy2 = restTemplate.getForEntity(new URL("http://localhost:" + port + "/enrolment/enrollee/"+id).toString(), Enrollee.class);
    	Enrollee enrolleeupdated = enrolleeEntitiy2.getBody();
    	
    	
    	assertNotNull(enrolleeupdated);
        
    }
    
    
    @Test
    public void delete_enrollee_with_status_200() throws URISyntaxException, RestClientException, MalformedURLException 
    {
    	
    	final String baseUrl = "http://localhost:"+port+"/enrolment/add/enrollee";
        URI uri = new URI(baseUrl);
        Enrollee enrolleetoAdd = new Enrollee();
        enrolleetoAdd.setName("test");
        enrolleetoAdd.setPhoneNo("98989765678");
        enrolleetoAdd.setStatus(true);
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Enrollee> request = new HttpEntity<>(enrolleetoAdd, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
    	
    	ResponseEntity<Enrollee> enrolleeEntitiy = restTemplate.getForEntity(new URL("http://localhost:" + port + "/enrolment/enrollee/1").toString(), Enrollee.class);
    	Enrollee enrollee = enrolleeEntitiy.getBody();
    	
    	enrollee.setName("admin1");
    	enrollee.setPhoneNo("45454545454");
        
    	restTemplate.delete(new URL("http://localhost:" + port + "/enrolment/delete/enrollee/1").toString(), enrollee);
    	
    	try {
    		ResponseEntity<Enrollee> enrolleeEntitiy2 = restTemplate.getForEntity(new URL("http://localhost:" + port + "/enrolment/enrollee/1").toString(), Enrollee.class);
        	Enrollee enrolleeupdated = enrolleeEntitiy2.getBody();
        	
       } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
       }
    	
    }
    
    

}