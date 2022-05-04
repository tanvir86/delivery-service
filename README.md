# Service for receiving deliveries 

## BUILD && RUN
### Using Commandline:
1. #### Dependency:
   - java 11
   - Maven
2. #### Build and Run
   ```aidl
   mvn clean package
   java -jar target/delivery-0.0.1-SNAPSHOT.jar
   ```
3. #### Access Endpoint and Documentation
   - Check http://localhost:8080/playground on browser

### Alternative: Using **Docker**
1. run test cases and build jar file
   1. run below command in project root directory
   ```aidl
   docker run -it --rm --name assesment-delivery-service-build -v "$(pwd)":/root -w /root adoptopenjdk/maven-openjdk11:latest mvn clean package
   ```
   2. this will build jar file ``delivery-0.0.1-SNAPSHOT.jar`` in `target` directory
2. Build Docker image using provided `Dockerfile` 
   1. run below command in project root directory
   ```aidl
   sudo docker build -t assesment-delivery:latest .
   ```
3. Start the application
   1. Run below command in project root directory
   ```aidl
   sudo docker run -d -p 8080:8080 -t assesment-delivery:latest
   ```
   2. This start the application in 8080 port
4. Check http://localhost:8080/playground on browser for endpoint documentation and api testing. 


## Further Improvement:
- Enable Spring boot actuator Prommetheus endpoint for Monitoring
- Add Apollo Federation server and configure this service to extend schema and log tracing

