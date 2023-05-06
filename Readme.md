# Mercans -- Integration Engineer Assignment


#Solution Overview
#####Technologies & Tools Used
	Java8
	Maven
	Springboot v2.6.3
	Opencsv v4.1
	Swagger
#####Working of Solution
	The solution loads the dynamic configuration from json file on application start.
	The csv file to be processed is kept in the resources folder in the code.
	The file name is also configured in application.yml
	so for now it is only processing 1 fixed file placed in resources folder.
	A Rest Endpoint is created to start the processing of csv file
	The code uses the default port available to run the embedded server.
	Following is the process os csv validation and data transformation
	1. Load the data from csv using opencsv
	2. Load the headers from first row of csv.
	3. Get the configuration of fields from configuration laoded at application start.
	4. Validate the data and create a list of good records.
	5. Add the error messages for invalid records in errorList and discard the invalid records.
	6. Create the data object of employees payload
	7. Create the processed Request to be conusmed by in house api.
	8. Return the response.
  

#Execution of Code
#####Spring Boot Application
	Code can also be executed by running the jar in target folder of the code
	OR
	Code can be imported in any IDE like eclipse or Intellij and run as springboot application
	
	To start the execution of csv file you need to hit a REST POST endpoint.
	http://localhost:8080/assignment/csv/processCsv
	
	You can hit this endpoint from swagger,postman or command line or any other tool you prefer