# Geocoder-Service
This is a simple implementation of Geocoder, a tool that helps convert coordinates to address and back, using Yandex Geocoder API
(https://yandex.ru/dev/maps/geocoder/)

Implementation:

 - PostgreSQL database
 - Java backend (Spring Boot)

Entry point: http://localhost:8080/

Actuator traces: http://localhost:8080/actuator/httptrace

# Prerequisites
In order to run this application you need to install: Docker.

Receive API key from yandex and paste it in 
`src/main/resources/application.properties`

# How to run it?
An entire application can be run with a command in a terminal:

<code>$ docker-compose up -d</code>

If you want to stop it use following command:

<code>$ docker-compose down</code>



