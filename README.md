# rest_spring
1.create an image<br />
mvn clean package docker:build -DpushImage

2.show container instance<br />
docker images

3.run the docker image<br />
docker run -p 80:8080 -td rest_practice:0.1.0
