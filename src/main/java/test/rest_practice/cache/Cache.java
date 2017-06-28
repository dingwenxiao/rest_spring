package test.rest_practice.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import test.rest_practice.model.Record;

@Component
public class Cache {
  List<Record> cacheList = new ArrayList<>();
  public static final int pageSize = 10;

  public Record getRecord(String id) {
    for (Record record : cacheList) {
      if (record.getValue().equals(id)) {
        return record;
      }
    }
    return null;
  }

  public List<Record> getAllRecords(int start, int offset) {
    List<Record> records = new ArrayList<>();
    int end = Math.min(offset, pageSize) + start;
    end = Math.min(cacheList.size(), end);
    for (int index = start; index < end; index++) {
      records.add(cacheList.get(index));
    }

    return records;
  }

  public Record remove(String value) {
    Iterator<Record> it = (Iterator) cacheList.iterator();
    while (it.hasNext()) {
      Record record = it.next();
      if (record.getValue().equals(value)) {
        it.remove();
        return record;
      }
    }
    return null;
  }

  public boolean saveRecord(Record record) {
    return cacheList.add(record);
  }

}
