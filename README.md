# Geocoder-Service
This is a simple implementation of Geocoder, a tool that helps convert coordinates to address and back, using Yandex Geocoder API
(https://yandex.ru/dev/maps/geocoder/)

Implementation:

 - PostgreSQL database
 - Java backend (Spring Boot)
 - Angular frontend
The entry point for a user is a website which is available under the address: http://localhost:4200/

# Prerequisites
In order to run this application you need to install: Docker.

Receive API key from yandex and paste it in 
`java\com\geocoderproxyservice\geo_main\GeoCoderFinal.java`

# How to run it?
An entire application can be ran with a command in a terminal:

<code>$ docker-compose up -d</code>

If you want to stop it use following command:

<code>$ docker-compose down</code>



