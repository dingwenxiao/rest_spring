package test.rest_practice.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import test.rest_practice.model.Record;

@Component
public class Cache {
  Map<String, Record> cacheMap = new HashMap<>();

  public Record getRecordById(String id) {
    return cacheMap.get(id);
  }


  public List<Record> getAllRecords() {
    List<Record> records = new ArrayList<>();
    for (Map.Entry<String, Record> entry : cacheMap.entrySet()) {
      records.add(entry.getValue());
    }
    return records;
  }


  public Record remove(String id) {
    return cacheMap.remove(id);
  }

  public boolean saveRecord(Record record) {
    cacheMap.put(record.getId(), record);
    return true;
  }

}
