
# Project Title

This repository holds the API for the data processing course of NHL Stenden



## Installation


```
  1. Download docker (https://www.docker.com/products/docker-desktop/)
  2. Extract the dump.zip folder
  3. Clone this repo
  4. Install maven (https://phoenixnap.com/kb/install-maven-windows)
  5. Navigate to the root of the project (/dataProcessing) in the terminal of your choice
  6. Run `mvn clean package`
  7. Run `docker-compose up -d` wait for this to complete before running step 6
  8. Run `java -jar .\target\dataProcessing-0.0.1-SNAPSHOT.jar`
  9. Test away

```


## Removal

```
  1. Press control and C to stop the jar file
  2. Run `docker-compose down -v` to remove the containers
```
    
    
## Documentation

This API uses Swagger to document its functionality. Once the API is running in your local system, access the documentation page through /swagger-ui.html on port 8080 of your local host.
[Documentation](http://localhost:8080/swagger-ui/index.html#/)


## Schemas Location
Schemas used for validation throught the project can be found here [schema's](https://github.com/CostaHiripis/dataProcessing/tree/master/src/main/resources/schema)

