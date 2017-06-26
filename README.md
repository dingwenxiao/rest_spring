# rest_spring
1.create an image
mvn clean package docker:build -DpushImage

2.show container instance
docker images

3.run the docker image
docker run -p 80:8080 -td rest_practice:0.1.0
