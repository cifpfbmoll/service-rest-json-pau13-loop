# Rest Service Quarkus Project

This project has been created with the command:
´´´
    mvn io.quarkus:quarkus-maven-plugin:1.13.1.Final:create -DprojectGroupId=edu.pingpong -DprojectArtifactId=restservicequarkus -DclassName="edu.pingpong.rest.json.CommentsResource" -Dpath="/comments" -Dextensions="quarkus-rest-client-jackson, quarkus-hibernate-orm"
´´´

This command is to genereate the same qurakus project bu0t with the jsonB dependency instead jackson:
```
    mvn io.quarkus:quarkus-maven-plugin:1.13.1.Final:create -DprojectGroupId=edu.pingpong -DprojectArtifactId=restservicequarkus -DclassName="edu.pingpong.rest.json.FruitResource" -Dpath="/fruits" -Dextensions="resteasy-jsonb"
```

>This project uses Quarkus, the Supersonic Subatomic Java Framework.</br>
>If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

To create you first quarkus project run the following command:

```
    mvn io.quarkus:quarkus-maven-plugin:1.13.1.Final:create -DprojectGroupId=edu.pingpong -DprojectArtifactId=quarkusgettingstarted -DclassName="edu.pingpong.quickstart.GreetingResource" -Dpath="/hello"
```

To run your application once has been created:
```
    mvnw compile quarkus:dev
```

---

## Table of Contents

1. [What means Development mode](#what-means-development-mode)
1. [Run Test Cases](#run-test-cases)
1. [Packaging and run the application](#packaging-and-run-the-application)
1. [Native](#native)
1. [Install Dependencies](#install-dependencies)
1. [Remember](#remember)
1. [External resources](#external-resources)

## What means Development mode 

```
quarkus:dev
```

- quarkus:dev runs Quarkus in development mode. This enables hot deployment with background compilation, which means that when you modify your Java files and/or your resource files and refresh your browser, these changes will automatically take effect. 

---

**[⬆ back to top](#table-of-contents)**

## Run Test Cases

```
    mnw test
```

You can run it by this command on your cmd or directly from your IDE.

> **By default, tests will run on port 8081 so as not to conflict with the running application.**

---

**[⬆ back to top](#table-of-contents)**

## Packaging and run the application

To make a package of the application:
```
    mvnw package
```

This will produce several outputs in the direcotry ```/target``` :

- ```getting-started-1.0.0-SNAPSHOT.jar``` - containing just the classes and resources of the projects, it’s the regular artifact produced by the Maven build - **IT IS NOT THE RUNNABLE JAR**;

- the ```quarkus-app``` directory which contains the ```quarkus-run.jar``` jar file - **BEING AN EXECUTABLE JAR**.

You can run the application bust just running the following command:
```
    java -jar target/quarkus-app/quarkus-run.jar
```

---

**[⬆ back to top](#table-of-contents)**

## Native 

You can create a native executable on windowns with the below command:
```
    mvnw package -Pnative -Dquarkus.native.container-build=true
```

> This allows you to create a native app without Graal by just using Mandrel.

And now to **compile the native app** you have to create a container to do so:
```
    mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```

---

**[⬆ back to top](#table-of-contents)**

## Install Dependencies

To list all the available dependencies for Quarkus:
```
    mvnw quarkus:list-extensions
```

To add a dependencies/extensions by name:
```
    mvnw quarkus:add-extension -Dextensions="<name ext>"
```

---

**[⬆ back to top](#table-of-contents)**

## Remember 

#### (Try not forget this concepts !)

- If you want to deploy your application somewhere (typically in a container), you need to deploy the whole ```quarkus-app``` directory

- Before running the application, don’t forget to stop the hot reload mode (hit ```CTRL+C```), or you will have a port conflict.

---

**[⬆ back to top](#table-of-contents)**

## External resources

- To test your quarkus application:

> https://github.com/rest-assured/rest-assured/wiki/Usage#response-body

---

**[⬆ back to top](#table-of-contents)**
