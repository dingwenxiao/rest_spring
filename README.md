# A Simple REST API Server

## Implementation techniques:<br />
*Front-end - html5, bootstrap, jquery etc.<br />
*back-end - Springboot <br />
*OS - Linux (Ubuntu Xenial 16.04) <br />
*Docker CE <br />
*Server is dockerized and hosted on AWS.<br />
http://ec2-18-220-15-36.us-east-2.compute.amazonaws.com/

## Implementation archetecture:<br />
*Desgin Pattern is Model View and Controller. <br />
*Front end function as displaying data and make REST request to backend.<br />
*Backend server provide REST API and response client with Json.<br />
(no database applied, only dummy cache class is used, so the data is stored in memory)

The maximum number of records is allowed for every request is 10. 

## Interaction Sequence Diagram

1) User enter/refresh - get all records

![Alt text](https://github.com/dingwenxiao/rest_spring/raw/master/screenshots/get_all_records.png)

2) User click 'show details' - get record. Details show up on popup window with info like 'is palindrome' and created date.

![Alt text](https://github.com/dingwenxiao/rest_spring/raw/master/screenshots/show_details.png)

3) User retrive a record - get record. On text feild, type text, the corresponding record will show up on the list.

![Alt text](https://github.com/dingwenxiao/rest_spring/raw/master/screenshots/search_record.png)

4) User click '-' - remove record. 

![Alt text](https://github.com/dingwenxiao/rest_spring/raw/master/screenshots/delete_record.png)

## How to build, deploy and access the app? 
Pre: install jdk 8, maven, docker-ce.

After Creating Amazon ec2 instance, on the instance you can do following:<br />
1.Create an image<br />
  mvn clean package docker:build -DpushImage

2.Show container instance<br />
  docker images<br />

3.Run the docker image<br />
  docker run -p 80:8080 -td rest_practice:0.1.0 <br />

4.the access url is the instance public ip address. for my poject is following<br />
http://ec2-18-220-15-36.us-east-2.compute.amazonaws.com/

## Testing
API Testing - test.rest_practice.RecordControllerTest

Unit Testing - test.rest_practice.RecordServiceTest

How to run? 

import the maven project to eclipse, and run above classes with junit.

Rest API Documentation
=====
| Method | URI | Data | HTTP Code | Response (in JSON) |
| ------ | --- | ---- | --------- | ------------------ |
| GET | /getRecords?start=0&&offset=10&&value= |  | 200 | [{"id":"bacsdfsdf-essdfef","value": "aba","isPalindrome": true,"date": "07-12-0032 07:10:04"},{"id":"ccasdfiis-sdfsdfsd", "value": "abcdef%^$","isPalindrome": false,"date": "07-12-0032 07:10:20"},{"id":"ccasdfiis-essdfef", "value": "cbc","isPalindrome": true,"date": "07-12-0032 07:10:11"}] |
| GET | /record/asdfad |  | 200 |{"id":"asdfad", "value": "aba","isPalindrome": true,"date": "07-12-0032 08:07:35"} |
| GET | /record/unknown |  | 404 | {"timestamp": 1498595279984,"status": 404,"error": "Not  Found","exception":"test.rest_practice.exception.RecordNotFoundException","message": "This record is not found in the  system","path": "/record/unknown"} |
| POST |/addRecord | {"id":"ccasdfiis-sdfsdfsd","value": "cbc","isPalindrome": false,"date": "07-12-0032 08:24:35"}| 201 |  |
| DELETE | /record/aba |  | 204 |  |
| DELETE | /record/unknown |  | 404 |  |
