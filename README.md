

# Web Framework Development

## Introduction
In this lab, we will enhance an existing web server by converting it into a fully functional web framework. The framework will support the development of web applications with backend REST services. The new framework will provide tools to define REST services using lambda functions, manage query values within requests, and specify the location of static files.

## Overview
This project aims to enhance an existing web server, which currently supports HTML files, JavaScript, CSS, and images, by converting it into a fully functional web framework. This framework will enable the development of web applications with backend REST services. The new framework will provide developers with tools to define REST services using lambda functions, manage query values within requests, and specify the location of static files.

## Project Scope and Features

### 1. GET Static Method for REST Services
Implement a `get()` method that allows developers to define REST services using lambda functions.

This feature will enable developers to define simple and clear routes within their applications, mapping URLs to specific lambda expressions that handle the requests and responses.

### 2. Query Value Extraction Mechanism
Develop a mechanism to extract query parameters from incoming requests and make them accessible within the REST services.
This functionality will facilitate the creation of dynamic and parameterized REST services, allowing developers to easily access and utilize query parameters within their service implementations.

### 3. Static File Location Specification
Introduce a `staticfiles()` method that allows developers to define the folder where static files are located.
The framework will then look for static files in the specified directory, such as `target/classes/webroot/public`, making it easier for developers to organize and manage their application's static resources.

## Implementation

- `http://localhost:8080/App/hello?name=Hann`

![name.png](images%2Fname.png)

- `http://localhost:8080/App/pi`

![pi.png](images%2Fpi.png)

- `http://localhost:8080/index.html`

![index.png](images%2Findex.png)

- Static Files

![html.png](images%2Fhtml.png)
![jpg.png](images%2Fjpg.png)
![js.png](images%2Fjs.png)
![png.png](images%2Fpng.png)
![txt.png](images%2Ftxt.png)

- Test

![test.png](images%2Ftest.png)
## How to Run
1. **Clone the repository**:
   ```bash
   git clone [https://github.com/Hajaku12/Lab2_webserver.git](https://github.com/Hajaku12/-Web-Framework-Development-for-REST-Services-and-Static-File-Management.git)
   ```

2. **Compile and Run the Server**:
   ```bash
   mvn package
   java -cp target/arep_lab1-1.0-SNAPSHOT.jar org.example.SimpleWebServer
   ```

3. **Access the Web Server**:
    - Open a web browser and navigate to `http://localhost:8080/index.html`.
    - The server will serve files from the `src/main/resources` directory.

4. **Access REST Services**:
    - To access the REST services, use the following endpoints:
      - `http://localhost:8080/App/hello?name=Hann`
      - `http://localhost:8080/App/pi`

5. **Stop the Server**:
    - Press `Ctrl + C` to stop the server.

6. **Run Unit Tests**:
    - To run the unit tests, use the following command:
      ```bash
      mvn test
      ```
      
## Dependencies
- **Maven**: The project uses Maven to manage dependencies and build the project.
- **JUnit 4**: The project uses JUnit 4 for unit testing.

## Configuration
- **Root Directory**: The server serves files from the `src/main/resources` to access each type of file it is necessary to enter the corresponding path depending on the type of file.
- **Port**: The server operates on port `8080` by default.
- **Static Files**: The server serves static files from the `src/main/resources/webroot` directory.
- **REST Services**: The server provides REST services under the `/App` path.
- **Query Parameters**: The server extracts query parameters from the request URL.
- **Lambda Functions**: Developers can define REST services using lambda functions.
- **Service Mapping**: The server maps URLs to lambda expressions for handling requests.
- **Service Implementation**: Developers can implement REST services using lambda functions.

---------

## Generating Project Documentation

1. **Generate the Site**
    - Run the following command to generate the site documentation:
      ```sh
      mvn site
      ```

2. **Add Javadoc Plugin for Documentation**
    - Add the Javadoc plugin to the `reporting` section of the `pom.xml`:
      ```xml
      <project>
        ...
        <reporting>
          <plugins>
            <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-javadoc-plugin</artifactId>
              <version>2.10.1</version>
              <configuration>
                ...
              </configuration>
            </plugin>
          </plugins>
        </reporting>
        ...
      </project>
      ```

    - To generate Javadoc as an independent element, add the plugin in the `build` section of the `pom.xml`:
      ```xml
      <project>
        ...
        <build>
          <plugins>
            <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-javadoc-plugin</artifactId>
              <version>2.10.1</version>
              <configuration>
                ...
              </configuration>
            </plugin>
          </plugins>
        </build>
        ...
      </project>
      ```

3. **Generate Javadoc Commands**
    - Use the following commands to generate Javadocs:
      ```sh
      mvn javadoc:javadoc
      mvn javadoc:jar
      mvn javadoc:aggregate
      mvn javadoc:aggregate-jar
      mvn javadoc:test-javadoc
      mvn javadoc:test-jar
      mvn javadoc:test-aggregate
      mvn javadoc:test-aggregate-jar
      ```

---------
## License
This project is licensed under the MIT License - see the `LICENSE.txt` file for details.

## Author
Hann Jang

