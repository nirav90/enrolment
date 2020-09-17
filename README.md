# enrolment

Refer swagger UI for all REST API : http://localhost:8080/swagger-ui.html#/

Please go with below flow.

Enrolment 

1. Create enrolment (add new enrolment) :  POST /enrolment/add/enrollee
2. Update enrolment (update exisiting enrolment) :  PUT /enrolment/update/enrollee/{id}
3. Get or View enrolment (view all enrolment or view enrolment by Id) : GET /enrolment/enrollee/{id} , GET /enrolment/enrollees
4. Delete enrolment (delete enrolment will also delete underlying dependents) : DELETE /enrolment/delete/enrollee/{id}


Depenedents  

1. Create Depenedents (add new Depenedents) :  POST /enrolment/add/enrollee
2. Update Depenedents (update exisiting Depenedents) :  PUT /enrolment/update/enrollee/{id}
3. Get or View Depenedents (view all Depenedents or view Depenedents by Id) : GET /enrolment/enrollee/{id} , GET /enrolment/enrollees
4. Delete Depenedents (it will delete Depenedents) : DELETE /enrolment/delete/enrollee/{id}




