# A Simple REST API Server

Implementation techniques:<br />
Front-end - html5, bootstrap, jquery etc.<br />
back-end - Springboot <br />
OS - Linux (Ubuntu Xenial 16.04) <br />
Docker CE <br />
the server is dockerized and hosted on AWS.<br />

Implementation archetecture:<br />
Desgin Pattern is Model View and Controller. <br />
the front end function as displaying data and make REST request to backend.<br />
the backend server provide REST API and response client with Json.<br />
(no database applied, only dummy cache class is used, so the data is stored in memory)


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
| POST |/addRecord | {"value": "cbc","isPalindrome": false,"date": "07-12-0032 08:24:35"}| 201 |  |
| DELETE | /record/aba |  | 204 |  |
| DELETE | /record/unknown |  | 404 |  |
