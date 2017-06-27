# rest_spring

How to build, deploy and access the app? 
================
After Creating Amazon ec2 instance, on the instance you can do following:<br />
1.create an image<br />
  mvn clean package docker:build -DpushImage

2.show container instance<br />
  docker images<br />

3.run the docker image<br />
  docker run -p 80:8080 -td rest_practice:0.1.0 <br />

4. the access url is the your instance public ip address.<br />

Rest API Documentation
=====
| Method | URI | Data | HTTP Code | Response (in JSON) |
| ------ | --- | ---- | --------- | ------------------ |
| GET | /orders/customerName=John |  | 200 | {"data": [{"orderId": 3,"orderTime": 1467794649000,"totalPrice": 42,"customerName": "John"}, {"orderId": 5,"orderTime": 1467794649000,"totalPrice": 85,"customerName": "John"}, {"orderId": 6,"orderTime": 1467794649000,"totalPrice": 85,"customerName": "John"}], "total": 4, "link-next": "/orders?page=2&customerName=John", "link-self": "/orders?customerName=John"} |
| GET | /orders/1 |  | 200 | {"orderId": 1,"orderTime": 1467794649000,"totalPrice": 22,"customerName": "Owen"} |
| GET | /orders/8 |  | 404 | {"error":"Not found 8"} |
| POST |/orders | 	{"totalPrice": 22, "customerName":"Stephen", orderTime": "2014-10-09 01:44:09"}	|201|	{"orderId": 163,"orderTime": 1412838000000,"totalPrice": 22,"customerName": "Stephen"}|
| POST |/orders |  | 400 | {"customerName": ["This field is required"]} |
| PUT/PATCH | /orders | {"totalPrice": 22, "customerName":"Stephen", orderTime": "2014-10-09 01:44:09"}	|200|	{"orderId": 164,"orderTime": 1412838000000,"totalPrice": 22,"customerName": "Stephen"}|
| PUT/PATCH |/orders |  | 400 | {"customerName": ["This field is required"]} |
| DELETE | /orders/1 |  | 200 | {"msg":"Deleted 1"} |
| DELETE | /orders/0 |  | 404 | {"error":"Not found 0"} |
