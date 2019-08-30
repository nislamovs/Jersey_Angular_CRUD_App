# Jersey_Angular_CRUD_App
Small CRUD App based on Jersey and Angular 7.

[![Build Status](https://travis-ci.org/nislamovs/Jersey_Angular_CRUD_App.svg?branch=master)](https://travis-ci.org/nislamovs/Jersey_Angular_CRUD_App)
[![Coverage Status](https://coveralls.io/repos/github/nislamovs/Jersey_Angular_CRUD_App/badge.svg?branch=master&service=github)](https://coveralls.io/github/nislamovs/Jersey_Angular_CRUD_App?branch=master)
[![Known Vulnerabilities](https://snyk.io//test/github/nislamovs/Jersey_Angular_CRUD_App/badge.svg?targetFile=UsersManager-FE/users-manager/package.json)](https://snyk.io//test/github/nislamovs/Jersey_Angular_CRUD_App?targetFile=UsersManager-FE/users-manager/package.json)

Status : development

#### Stack:

    Embedded Jetty with https 
    Jersey
    Springboot 2
    Spring Data (audit, revisions...)
    Logback
    Lombok
    Swagger
    Docker
    Angular7

#### How to build:
 
**Backend:**

- Go to `JerseyServer_BE` directory and run command `./gradlew clean build` or `gradle clean build`.

- Then go to `Jersey_Angular_CRUD_App/JerseyServer_BE/build/` directory and find there file `jerseyexample-1.0.0.jar`.

- Run it with `java -jar jerseyexample-1.0.0.jar`.

- The service will launch on `8081` port. Endpoints will be accessible from `https://localhost:8081/api/`.

- Spring actuator endpoints will be available from `https://localhost:8081/actuator`.

- H2 in-memory storage is available from `https://localhost:8080/dbconsole`.

- Backend could be launched from IDE as well. 

**Frontend:**

- Go to `UsersManager-FE` and run `ng serve`.
 
- Open browser, type `localhost:4200` in url field and You'll see frontend part.

**API Docs:**

- Swagger ui will be accessible from `https://localhost:8081/swagger-ui.html`.

- Swagger json will be accessible from `https://localhost:8081/api/swagger.json`.

- If You need API docs in pdf/html format, run: ```./gradlew clean swaggerDoc```. Then go to ```Jersey_Angular_CRUD_App/JerseyServer_BE/build/asciidoc```, where You will find documentation in both formats.

**Docker:** 

- To build docker image, go to project root directory and there run ```./gradlew clean build docker```

- To export image in separate tar archive, go to _docker scripts directory_ ```Jersey_Angular_CRUD_App/scripts/docker```, and there run script ```create_image.sh```. You will find docker image (in tar format) in build directory: ```Jersey_Angular_CRUD_App/JerseyServer_BE/build/docker```.  

#### Certificates:

If You want to regenerate certificates, You should visit folder `Scripts/system/certificates` and 
there run script `generateCertificates.sh`. If You want to change key params, You should change those in script.