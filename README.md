# Task

## Description
Simple web application integrating REST service with external message broker system.

## Requirements
1. Install latest JDK
2. Install Docker
3. Install Maven
4. Install Git

## Building and Running

1. `git clone https://github.com/wiktorkielar/task.git`
2. `./mvnw clean install`
3. `docker run -d --name rabbitmq-container -p 4369:4369 -p 5671:5671 -p 5672:5672 rabbitmq:3`
4. `java -jar target/task.jar` 


## Running Unit Tests
1. `./mvnw test`

## Running Integration Test
1. `docker run -d --name rabbitmq-container -p 4369:4369 -p 5671:5671 -p 5672:5672 rabbitmq:3`
2. `java -jar target/task.jar`
3. `./mvnw test -Dtest=ApplicationTest`

## Running Performance Test
1. `./mvnw gatling:test`