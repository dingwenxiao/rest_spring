package test.rest_practice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import test.rest_practice.cache.Cache;
import test.rest_practice.model.Record;
import test.rest_practice.service.RecordService;

public class RecordServiceTest {
  
  RecordService recordService;
  

  @Before
  public void init() {
     recordService = new RecordService();
     Cache cache = new Cache();
     recordService.setCache(cache);
  }

  @Test
  public void addRecordTest() {
    Record record1 = new Record("aba", Boolean.TRUE, new Date());
    Record record = recordService.addRecord(record1);
    assertEquals(record1.getValue(), record.getValue());
  }

  @Test
  public void getRecordsTest() {
    Record record1 = new Record("aba", Boolean.TRUE, new Date());
    Record record2 = new Record("bac", Boolean.FALSE, new Date());
    recordService.addRecord(record1);
    recordService.addRecord(record2);
    List<Record> actualRecords = recordService.getRecords();
    assertFalse(actualRecords.indexOf(record1) == -1);
  }

  @Test
  public void getRecordTest() {
    Record record1 = new Record("aba", Boolean.TRUE, new Date());
    Record record2 = new Record("bac", Boolean.FALSE, new Date());
    recordService.addRecord(record1);
    recordService.addRecord(record2);
    Record actualRecord = recordService.getRecord(record1.getValue());
    assertEquals(actualRecord, record1);
  }

  @Test
  public void deleteRecord() {
    Record record1 = new Record("aba", Boolean.TRUE, new Date());
    Record record2 = new Record("bac", Boolean.FALSE, new Date());
    recordService.addRecord(record1);
    recordService.addRecord(record2);
    recordService.deleteRecord(record1.getValue());
    List<Record> actualRecords = recordService.getRecords();
    assertEquals(actualRecords.size(), 1);
  }
  
}
