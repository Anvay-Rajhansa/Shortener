# shortener
HTTP service that serves to shorten URLs.

###Prerequisites - 
```
 - Java8  
 - maven
```

###Used framework and libraries- 
```
 - Spring boot  
 - Spring security(Basic authentication)     
 - Spring data JPA  
 - H2database  
 - Apache commons-lang3  
```

###Set Up - 
 Clone the repository - 
```
git clone https://github.com/Anvay-Rajhansa/shortener.git
```
 
 This will create 'shortener' directory in current directory where clone is taken then go to this shortener directory 
``` 
 cd shortener
```
 You can see following files in this directory
``` 
 - pom.xml    
 - README.md  
 - src
```
 Run following mvn command to download dependencies and generating build.
```
mvn clean install
```
 This will download all the dependencies and will generate the executable jar in target directory.
 Jar with name 'shortener-0.0.1-SNAPSHOT.jar' will be present in target directory.

###Excecution - 
After done with primary setup you can start the execution of the application with this command -
```    
mvn spring-boot:run
```
This will start the application on 8080 port.

###Usage - 
In total, there are three exposed APIs and working of which is explained with curl command -

####/account 
```
Request - curl -H "Content-Type: application/json" -X POST -d '{"accountId":"test123"}' http://localhost:8080/account
Response - {"status":true,"description":"Your account is opened","password":"Ya4JCDab"}
```
Password provided is generated in response for given account ID which is required for next two API calls, for basic authentication.  

####/register
```
Request - curl -H "Content-Type: application/json" -X POST -d '{"url":"https://www.google.co.in", "redirectType":301}' --user test123:Ya4JCDab http://localhost:8080/register
Response - {"shortUrl":"http://localhost:8080/tn2zFu"}
```
####/statistic/{AccountId}
```
Request - curl -H "Content-Type: application/json" -X GET --user test123:Ya4JCDab http://localhost:8080/statistic/test123
Response - {"https://www.google.co.in":1} 
```
####Url Redirection 
```
You can actually check if link is being redirected by hitting the short url in browser   
which is available in respose of /register API call.
```
  
 

