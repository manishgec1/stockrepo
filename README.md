# stockrepo
Stock Application to display UP and DOWN Stock bases on time.


I have created 3 Projects.
I.	 Spring Boot Back End: Eureka server
II.	 Spring Boot Back End : StockApplication.
III. React UI Application : stock-react-springboot.

I.	To run : Eureka server:
----------------------------------------------------------
1.	Go to Project directory ..\.. \SpringBootEurekaNamingServer
2.	Run maven command 
3.	$ mvn spring-boot:run
4.	APP URL: http://localhost:8761/
 

II.	To run StockApplication:
----------------------------------------------------------
1.	Go to Project directory ..\.. \StockApplication
2.	 Run maven command 
                      $ mvn spring-boot:run -Dmaven.test.skip=true

3.	Application will run on port :9090
URL real-time stock pricing bases on evettime:
http://localhost:9090/api/v1/stockspricing

JSON Response:
 
URL real-time stock:
http://localhost:9090/api/v1/stocks

Response :
 

URL to find stock based on ID:
http://localhost:9090/api/v1/stocks/1

Response


Actuator API for Health check
http://localhost:9090/actuator/health

Response
 
JUNIT TEST CASES RUN RESULT


III.	React UI Application RUN.
----------------------------------------------------------
              $ ..\..\stock-react-springboot\stock-react-ui> npm start

URL For React UI : http://localhost:3000/stockspricing
 

It is not the part of requirement but I feel if I can provide. I have extended API to View Backend real time data for admin users in user iterface.
URL : http://localhost:3000/stocks
 
URL to add stock by backed Team it there is some missing Stock;

http://localhost:3000/add-stock/_add
 
