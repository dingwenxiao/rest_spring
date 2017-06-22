package test.rest_practice.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.rest_practice.model.Greeting;
import test.rest_practice.model.Record;
import test.rest_practice.service.RecordService;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    RecordService recordService;
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping (method =  RequestMethod.GET)
    public List<Record> getAllRecords(Model model)
    {
        //application code
        return recordService.getRecords();
    }
     
    @RequestMapping (method =  RequestMethod.POST)
    public String addRecord(String message)
    {
        //application code
        return message;
    }
     
//    @RequestMapping (method =  RequestMethod.PUT)
//    public String updateEmployee(EmployeeVO employee)
//    {
//        //application code
//        return "employeesDetail";
//    }
     
    @RequestMapping (method =  RequestMethod.DELETE)
    public String removeRecord(@RequestParam("id") String recordId)
    {
        return "employeesList";
    }
}
