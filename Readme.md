Simple example dockerizing a Spring Boot service which leverages a mongodb storage.

Start the containers with

```
$ docker-compose up
```

Add some data. Note, your docker host might have a different IP, check it with echo $DOCKER_HOST command.

```
$ echo '{"name":"St. Bonifatiuscollege ", "city":"Utrecht"}' | http post http://192.168.99.100:8080/schools
```

Query:

```
$ http get http://192.168.99.100:8080/schools/search/findByCityIgnoreCase\?city\=utrecht
```

```json
{
    "_embedded": {
        "schools": [
            {
                "_links": {
                    "school": {
                        "href": "http://192.168.99.100:8080/schools/56e275b4e4b02d414c2d87bd"
                    },
                    "self": {
                        "href": "http://192.168.99.100:8080/schools/56e275b4e4b02d414c2d87bd"
                    }
                },
                "city": "Utrecht",
                "name": "St. Bonifatiuscollege "
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://192.168.99.100:8080/schools/search/findByCityIgnoreCase?city=utrecht"
        }
    }
}

```

In order to deploy it to AWS check the blog post: [http://zoltanaltfatter.com/2016/03/11/dockerized-spring-boot-service-on-aws/](http://zoltanaltfatter.com/2016/03/11/dockerized-spring-boot-service-on-aws/)
