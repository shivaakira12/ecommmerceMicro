1. What is Service Discovery and Why we need this ?
* Service Discovery is a directory where it helps one application to find the another, even their addresses changes.
* Lets take an example : Lets suppose I need to call Mom instead of searching in the contacts app we can search on the search bar or tap on name right.
So in a microservice system, a service looks up a name such as inventory-serivce, rather looking for the Ip Address and Port.


More precisely, in a real projects one services may restart or moves to different positions or machines and they may grow as the traffic increases they may have more instances.

With Discovery Serivce:
* This will register the name and address of service when it starts.
* A calling services looks up a name when it needs to make a request.
* If there are several instances load balancing can be happen.

Simply : This Discovery server register all the services when they starts and now order service is request to the inventory now order service asks the discovery server, HEY Discovery Server i need to make to call to check the inventory stock now discovery server responds back by checking the what are instances of inventory service is alive as per the name requested by the order service. This is the simple flow.


How to implement this in Spring boot microservices ?

a. We need to add the dependency for the Netflix Eureka server inside the Discovery Service from the Spring Cloud.

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

b. We need to add the dependency in the main application class @EnableEurekaServer but later spring version no need to add this spring automatically configures.
```java
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}
```

c. We need to configure the application.properties file for the Eureka server.
```properties
server.port=8761
eureka.client.register-with-eureka=false  // This is because we are not registering the discovery server with itself.
eureka.client.fetch-registry=false // This is because we are not fetching the registry from the discovery server.
```

d. When we run the Discovery server application it will start on port 8761 and we can access the Eureka dashboard at http://localhost:8761.

e. Now we need to register the microservices with the Eureka server. For that, we need to add the Eureka client dependency in the microservices.
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

f. Inside the appplication .properties file of the microservices, we need to add the following configuration to register with Eureka server.
```properties
spring.application.name=inventory-service // This is the name of the service that will be registered with Eureka server.
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/ // This is the URL of the Eureka server where the microservice will register itself.
```

g. Now we need to add the @EnableEurekaClient annotation in the main application class of the microservices.
```java
@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
}
```

h. Now when we run the microservices, they will register themselves with the Eureka server and we can see them in the Eureka dashboard.
and we dont need to use the IP address and port of the microservices to call them, we can use the service name instead. The Eureka server will take care of resolving the service name to the correct IP address and port.  

What is use of @LoadBalanced annotation in Spring Boot microservices? And how to use it ?
* The @LoadBalanced annotation is used in Spring Boot microservices to enable client-side load balancing.
* When a service makes a request to another service, it can have multiple instances running. 
* The @LoadBalanced annotation allows the client to distribute the requests across these instances, improving performance and reliability.
* To use the @LoadBalanced annotation, you need to create a RestTemplate bean and annotate it with @LoadBalanced. This will allow the RestTemplate to resolve service names to their corresponding instances registered in the Eureka server.
Here's an example of how to use the @LoadBalanced annotation in a Spring Boot microservice:
```java
@Configuration
class ClientConfig {
    @Bean
    @LoadBalanced
    RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}

restClientBuilder.build()
    .get()
    .uri("http://inventory-service/inventory/123")
    .retrieve()
    .body(String.class);
```

3. What if the Service Discovery goes down ?
* If the Service Discovery goes down, the microservices which are still working fine for some time using cached info(cached information means the information is stored locally) but new services cannot register themselves also the existing 
services cannot discover each other. This can lead to failures in communication between services.
Solution : 
* We can keep the Eureka servers.

4. If the order service is has the inventory service cachedd and what if the inventory service goes down and order service tries to call the inventory service what will happen ?
* If the inventory service goes down and the order service tries to call it, the cached information wont work untit the instance is running again.
* That's why we need to use the muitple instances of the services and load balancing.