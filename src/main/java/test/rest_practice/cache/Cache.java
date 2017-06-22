package test.rest_practice.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.rest_practice.model.Record;

public class Cache {
  Map<String, String> cacheMap = new HashMap<>();

  public String getRecordById(String id) {
    return cacheMap.get(id);
  }


  public List<Record> getAllRecords() {
    List<Record> records = new ArrayList<>();
    for (Map.Entry<String, String> entry : cacheMap.entrySet()) {
      Record record = new Record(entry.getKey(), entry.getValue());
      records.add(record);
    }
    return records;
  }

}
