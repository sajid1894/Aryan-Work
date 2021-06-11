# Products Restful Webservice

This is a sample Java / Maven / Spring Boot (version 2.5.0) application that can be used as a starter for creating a microservice to request dresses that have a price reduction.

## How to Run 

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/price-reduction-0.0.1-SNAPSHOT.war
or
        mvn spring-boot:run
```

Once the application runs you should see something like this

```
2021-06-11 13:18:04.658  INFO 7108 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 1221 (http) with context path '/price-reduction'
2021-06-11 13:18:04.667  INFO 7108 --- [  restartedMain] com.java.app.PriceReductionApplication   : Started PriceReductionApplication in 2.266 seconds (JVM running for 3.312)
```

## About the Service

The array of products should only contain products with a price reduction and should be sorted to show the highest price reduction first. Price reduction is calculated using price.was - price.now.
 
Here is what this little application demonstrates: 

* Full integration with the latest **Spring Boot** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* All APIs are "self-documented" by Swagger2 using annotations 

### Retrieve list of products

```
GET http://localhost:1221/price-reduction/fetchProducts?labelType=ShowWasNow

http-status: 200
connection: keep-alive 
content-type: application/json 
date: Fri,11 Jun 2021 07:53:46 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked 

Response Body: 
[
  {
    "productId": "4873363",
    "title": "Ghost Astrid Floral Dress, Navy Clusters",
    "colorSwatches": [
      {
        "color": "Blue",
        "rgbColor": "0000FF",
        "skuId": "238346446"
      }
    ],
    "nowPrice": "£74",
    "priceLabel": "Was £149, now £74"
  },
  {
    "productId": "4823284",
    "title": "Hobbs Astraea Floral Dress, Navy/Ivory",
    "colorSwatches": [
      {
        "color": "Blue",
        "rgbColor": "0000FF",
        "skuId": "238448191"
      }
    ],
    "nowPrice": "£99",
    "priceLabel": "Was £169, now £99"
  }
]
```

## Flow of the CodeBase

1. c.j.a.c.PriceReductionController         : Request Recielved from client: http://localhost:1221/price-reduction/fetchProducts
2. c.j.a.c.PriceReductionController         : Inside PriceReductionController Class....
3. c.j.a.c.PriceReductionController         : Calling getProducts() method....
4. c.j.a.c.PriceReductionController         : Fetching Products from Service....
5. c.j.a.service.PriceReductionServiceImpl  : Inside PriceReductionServiceImpl Class....
6. c.j.a.service.PriceReductionServiceImpl  : Calling fetchAllProducts(ShowWasNow) method....
7. c.j.a.service.PriceReductionServiceImpl  : Fetching Products from Repository....
8. c.j.a.r.PriceReductionRepositoryImpl     : Inside PriceReductionRepositoryImpl Class....
9. c.j.a.r.PriceReductionRepositoryImpl     : Calling fetchAllProducts() method....
10. c.j.a.r.PriceReductionRepositoryImpl     : Fetching productUrl and productUrlKey from 11. api-url.properties file....
12. c.j.a.r.PriceReductionRepositoryImpl     : Hitting the Product API url: https://api.johnlewis.com/search/api/rest/v2/catalog/products/search/keyword?q=dresses&key=AIzaSyDD_6O5gUgC4tRW5f9kxC0_76XRC8W7_mI
13. c.j.a.r.PriceReductionRepositoryImpl     : Got the response code: 200 OK
14. c.j.a.r.PriceReductionRepositoryImpl     : Returning the Product response to the Service....
15. c.j.a.service.PriceReductionServiceImpl  : Fetched Done from Repository....
16. c.j.a.service.PriceReductionServiceImpl  : Processing the fetched Products....
17. c.j.a.service.PriceReductionServiceImpl  : Processing Done....
18. c.j.a.service.PriceReductionServiceImpl  : Sorting the fetched Products based on reduced price....
19. c.j.a.service.PriceReductionServiceImpl  : Sorting Done....
20. c.j.a.service.PriceReductionServiceImpl  : Sending Processed Products (DTO) to the controller....
21. c.j.a.c.PriceReductionController         : Fetched Done from Service....
22. c.j.a.c.PriceReductionController         : Returing the response to the client....

### To view Swagger 2 API docs

Run the server and browse to [http://localhost:1221/price-reduction/api-docs.html](localhost:1221/price-reduction/api-docs.html)

Below is the snapshot the of the api documentation,

![ScreenShot](https://github.com/sajid1894/Aryan-Work/blob/main/price-reduction/images/image1.JPG)

You can tryout the api endpoints by **Clicking the Endpoint Boxes** and after that hitting the `tryout` button,

![ScreenShot](https://github.com/sajid1894/Aryan-Work/blob/main/price-reduction/images/image2.JPG)

# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc.

# Generated Javadocs

Javadocs are gererated using the maven-javadoc-plugin and accessable through `index.html` file in the folder `/target/apidocs`

Below is the snapshot the of the javadoc,

![ScreenShot](https://github.com/sajid1894/Aryan-Work/blob/main/price-reduction/images/image3.JPG)
