# rest_spring

How to build, deploy and access the app? 
================
After Creating Amazon ec2 instance, on the instance you can do following:<br />
1.Create an image<br />
  mvn clean package docker:build -DpushImage

2.Show container instance<br />
  docker images<br />

3.Run the docker image<br />
  docker run -p 80:8080 -td rest_practice:0.1.0 <br />

4. the access url is the your instance public ip address.<br />

Rest API Documentation
=====
| Method | URI | Data | HTTP Code | Response (in JSON) |
| ------ | --- | ---- | --------- | ------------------ |
| GET | /getAllRecords |  | 200 | [{"value": "aba","isPalindrome": true,"date": "07-12-0032 07:10:04"},{"value": "abcdef%^$","isPalindrome": false,"date": "07-12-0032 07:10:20"},{"value": "cbc","isPalindrome": true,"date": "07-12-0032 07:10:11"}] |
| GET | /record/aba |  | 200 |{"value": "aba","isPalindrome": true,"date": "07-12-0032 08:07:35"} |
| GET | /record/unknown |  | 404 | {"timestamp": 1498595279984,"status": 404,"error": "Not  Found","exception":"test.rest_practice.exception.RecordNotFoundException","message": "This record is not found in the  system","path": "/record/unknown"} |
| POST |/addRecord | {"value": "bcdef%^$","isPalindrome": false,"date": "07-12-0032 08:24:35"}| 200 | {"value":"bcdef%^$","isPalindrome": false,"date": "07-12-0032 08:24:35"} |
| POST |/orders |  | 400 | {"customerName": ["This field is required"]} |
| PUT/PATCH | /orders | {"totalPrice": 22, "customerName":"Stephen", orderTime": "2014-10-09 01:44:09"}	|200|	{"orderId": 164,"orderTime": 1412838000000,"totalPrice": 22,"customerName": "Stephen"}|
| PUT/PATCH |/orders |  | 400 | {"customerName": ["This field is required"]} |
| DELETE | /orders/1 |  | 200 | {"msg":"Deleted 1"} |
| DELETE | /orders/0 |  | 404 | {"error":"Not found 0"} |
