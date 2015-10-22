people-restful-integration-test
===============================
An integration test project for people-restful service

### How to run the integration tests
* Start people-restful REST service
 
 Please refer to [people-restful] (https://github.com/coder-weiru/people-restful) document to start the service.
 
* Using maven, the all tests can be run by ```mvn clean test``` 

* Using Spring Tool Suite
 1. git clone https://github.com/coder-weiru/people-restful-integration-test
 2. import people-restful-integration-test into STS via **existing maven projects into workspace** option.
 3. Find ```PeopleServiceRestClientIntegrationTest``` and run as JUnit Test 