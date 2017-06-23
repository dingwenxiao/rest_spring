package test.rest_practice.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import test.rest_practice.model.Record;
import test.rest_practice.service.RecordService;

@Controller
public class RestController {

  @Autowired
  RecordService recordService;


  @RequestMapping(value = "/getAllRecords", method = RequestMethod.GET)
  public List<Record> getAllRecords() {
    return recordService.getRecords();
  }

  @RequestMapping("/record/{id}")
  public @ResponseBody Record getRecord(@PathVariable(value = "id") String id) {
    return recordService.getRecordById(id);
  }

  @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
  public @ResponseBody Record addRecord(@RequestBody Record record) {
    Record responseRecord = recordService.addRecord(record);
    return responseRecord;
  }

  // @RequestMapping (method = RequestMethod.PUT)
  // public String updateEmployee(EmployeeVO employee)
  // {
  // //application code
  // return "employeesDetail";
  // }

  @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> removeRecord(@PathVariable(value = "id") String recordId) {
    if (recordService.deleteRecord(recordId)) {
      return new ResponseEntity(HttpStatus.OK);
    }
      return new ResponseEntity(HttpStatus.NOT_FOUND);
  }
}
