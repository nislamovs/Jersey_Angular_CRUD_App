# Jersey_Angular_CRUD_App
Small CRUD App based on Jersey and Angular 7.

Status : development

####Stack:

    Embedded Jetty with https 
    Jersey
    Springboot 2
    Spring Data
    Logback
    Lombok
    Swagger
    Docker
    Angular7

####How to build:
 
**Backend:**

- Go to `JerseyServer_BE` directory and run command `gradle build`.

- Then go to `Jersey_Angular_CRUD_App/JerseyServer_BE/build/libs/` directory and find there file `jerseyexample-1.0.0.jar`.

- Run it with `java -jar jerseyexample-1.0.0.jar`.

- The service will launch on `8080` port. Endpoints will be accessible from `https://localhost:8080/api/`.

- Swagger ui will be accessible from `https://localhost:8080/swagger-ui.html`.

- Swagger json will be accessible from `https://localhost:8080/api/swagger.json`.

- Spring actuator endpoints will be available from `https://localhost:8080/actuator`.

- H2 in-memory storage is available from `https://localhost:8080/dbconsole`.

- Backend could be launched from IDE as well. 

**Frontend:**

- Go to `UsersManager-FE` and run `ng serve`.
 
- Open browser, type `localhost:4200` in url field and You'll see frontend part.

####Certificates:

If You want to regenerate certificates, You should visit folder `Scripts/system/certificates` and 
there run script `generateCertificates.sh`. If You want to change key params, You should change those in script.