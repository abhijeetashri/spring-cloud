# Product Manager

Product Manager is a micro-service for
* Getting all brands
* Updating a brand name of a product
* Filter products by brand & price


## Technical Requirements

* Java
* H2 Database
* Spring Boot
* Spring JPA
* Spring Cloud


## Installation

### Eclipse (local)

1. Run  ``` mvn clean install ```
2. Make a config repository with the properties file (.properties/.yml) on local file system. Existing ones are in [configuration folder](https://github.com/abhijeetashri/configuration)
3. Run [Eureka server](https://github.com/abhijeetashri/eureka-server)
4. Run [Config server](https://github.com/abhijeetashri/config-server)
5. Run Product Manager
6. Test with http://localhost:[port]/products/all if service is UP or not. It will give all listed down products.
7. The data of each product could be changed in the data.sql file

## License
No License