# enrolment

Refer swagger UI for all REST API : http://localhost:8080/swagger-ui.html#/

Please go with below flow.

Enrolment 

1. Create enrolment (add new enrolment) :  POST /enrolment/add/enrollee
2. Update enrolment (update exisiting enrolment) :  PUT /enrolment/update/enrollee/{id}
3. Get or View enrolment (view all enrolment or view enrolment by Id) : GET /enrolment/enrollee/{id} , GET /enrolment/enrollees
4. Delete enrolment (delete enrolment will also delete underlying dependents) : DELETE /enrolment/delete/enrollee/{id}


Depenedents  

1. Create Depenedents (add new Depenedents) :  POST /dependents/add/{enrolleeId}/dependents
2. Update Depenedents (update exisiting Depenedents) :  PUT /dependents/update/dependents/{id}
3. Get or View Depenedents (view all Depenedents or view Depenedents by Id) : GET /dependents/dependents, GET /dependents/dependents/{dependentsId}
4. Delete Depenedents (it will delete Depenedents) : DELETE /dependents/delete/dependents/{id}

To run this application please follow below steps.

update mySql database properties in application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/enrolment
spring.datasource.username=#######
spring.datasource.password=#######

Connect to MySql server and create database :
mysql -u root -p
mysql> CREATE DATABASE enrolment;


