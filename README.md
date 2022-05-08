# Lulo Bank: Code Challenge. Transaction Authorizer
# lulo-jlchr-assessment


## Used Technologies

- Docker
- Maven
- Java 17
- Lombok

## Build & Deploy

For making posible the build of the application, it is necessary to complete the following steps:
1. Clone the project
2. Stand in the project root, and type this command:
``` mvn clean package ```
3. To create the docker image, you need to run this command again in the project root:
``` docker build -t lulo-assessment-docker.jar . ```
4. Finally, to create the container based on the image in the previous step, execute this command:
``` docker run -it -p 8080:8080 lulo-assessment-docker.jar```



#### Request information

Once the container is running, the command pront will be waiting for the kind of operation that the program will authorize or cathegorize.

You can use the following commands in order to test the different type of possible results:
```{"account": {"id": 1, "active-card": true, "available-limit": 100}}```
```{"account": {"id": 1, "active-card": true, "available-limit": 100}} ```
```{"transaction": {"id": 1, "merchant": "Burger King", "amount": 20, "time":"2019-02-13T10:00:00.000Z"}} ```

Please be advised that the program will receive the complete json in one line. 

Juan Luis Chitiva Rivera


