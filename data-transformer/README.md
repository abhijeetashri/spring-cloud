# Data Transformer

Data Transformer is a micro-service for
* Alphatize incoming JSON - sort keys in ascending order
* Flattening of JSONArray objects in incoming JSON
* Checking system status - cores available, root directories and their available space


## Installation

### Eclipse (local)

1. Run  ``` mvn clean install ```
2. Make a config repository with the properties file (.properties/.yml) on local file system. Existing ones are in [configuration folder](https://github.com/abhijeetashri/configuration)
3. Run [Eureka server](https://github.com/abhijeetashri/eureka-server)
4. Run [Config server](https://github.com/abhijeetashri/config-server)
5. Run Data Transformer
6. Test with http://localhost:[port]/hello if service is UP or not

## License
No License