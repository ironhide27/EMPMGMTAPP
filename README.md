<h2><u>EMPLOYEE MANAGEMENT SERVICE</u></h2>
Description : Microservice for Management of Employee Data

DB - H2 Embedded. File store based. Config is present in application-*.yml files(Under spring.datasource prefix)

Actuator Base URL : http://hostname:9000/actuator/*

Swagger UI URL : http://hostname:9000/swagger-ui.html

H2 Console while on local profile : http://localhost:9000/h2-console/

App Startup : java -Dspring.profiles.active=local -jar empMgmt-0.0.1-SNAPSHOT.jar
                                    