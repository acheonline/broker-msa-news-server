# Broker-Msa-News-Server application

### Main description

Application in cycled mode send messages with unique UUID, LocalDateTime.now() date_time and specific description, which contains UUID also.
POST HTTP controller host and fixedDelay rate of requests manages via environment variables.

If HTTP connection cannot been established HttpHostConnectException will be thrown wth certain exception message.

### Environment variables for integration(e.g. look below)


| variable     | desciption                                               | example                          |
|--------------|----------------------------------------------------------|----------------------------------|
| OUTPUT_HOST  | Specific host to be integrated with POST REST Controller | http://localhost:8080/news/post/ |
| OUTPUT_DELAY | Scheduled delay of POST request (in milliseconds)        | 5000                             |
  


### Docker Hub

* [Broker-Msa-News-Server](https://hub.docker.com/repository/docker/achernyavskiy0n/broker-msa-news-server)