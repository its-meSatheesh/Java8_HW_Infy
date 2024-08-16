# Springboot-Points Calculator

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.api.pointscalculator.PointsCalculatorApplication` class from your IDE.

## REST end point
eg: - http://localhost:8080/api/calculatePoints

## Sample Request Body
```
[
{
"customerId": "Customer10001",
"firstName": "Mike",
"lastName": "Grame",
"items": [
{
"itemId": "Item1000",
"description": "item1",
"qty": "4",
"purchaseDate": "2024-05-23 12:00",
"itemPrice": "180"
},
{
"itemId": "Item1010",
"description": "item11",
"qty": "4",
"purchaseDate": "2024-05-23 12:00",
"itemPrice": "50.99"
},					
{
"itemId": "Item1001",
"description": "item3",
"qty": "1",
"purchaseDate": "2024-06-23 12:00",
"itemPrice": "110"
}	,
{
"itemId": "Item1001",
"description": "item2",
"qty": "1",
"purchaseDate": "2024-07-23 12:00",
"itemPrice": "100"
}						
]
},
{
"customerId": "Customer10002",
"firstName": "Greg",
"lastName": "Martin",
"items": [
{
"itemId": "Item1000",
"description": "item1",
"qty": "1",
"purchaseDate": "2024-05-13 10:00",
"itemPrice": "150"
},
{
"itemId": "Item1010",
"description": "item11",
"qty": "4",
"purchaseDate": "2024-05-23 12:00",
"itemPrice": "50.99"
}				
]
}
]
```

## Sample Response
```
[
{
"customerId": "Customer10001",
"rewardDetails": {
"JUNE2024": 70,
"MAY2024": 211,
"JULY2024": 50
},
"totalRewards": 331
},
{
"customerId": "Customer10002",
"rewardDetails": {
"MAY2024": 151
},
"totalRewards": 151
}
]
```
