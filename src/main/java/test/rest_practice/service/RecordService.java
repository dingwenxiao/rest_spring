package test.rest_practice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.rest_practice.cache.Cache;
import test.rest_practice.model.Record;

@Service
public class RecordService {

  @Autowired
  Cache cache;

  public List<Record> getRecords() {
    return cache.getAllRecords();
  }

  public Record getRecordById(String id) {
    return cache.getRecordById(id);
  }

  public boolean deleteRecord(String id) {
    return cache.remove(id)!=null;
  }

  public Record addRecord(Record record) {
    Boolean isPalindrome = isPalindrome(record.getValue());
    Record newRecord = new Record(UUID.randomUUID().toString(), record.getValue(), isPalindrome);
    boolean isSaved = cache.saveRecord(newRecord);
    return isSaved ? newRecord : new Record(null, null, null);
  }

  public boolean isPalindrome(String s) {
    if (s == null || s.isEmpty()) {
      return true;
    }
    int head = 0, tail = s.length() - 1;
    char cHead, cTail;
    while (head <= tail) {
      cHead = s.charAt(head);
      cTail = s.charAt(tail);
      if (!Character.isLetterOrDigit(cHead)) {
        head++;
      } else if (!Character.isLetterOrDigit(cTail)) {
        tail--;
      } else {
        if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
          return false;
        }
        head++;
        tail--;
      }
    }

    return true;
  }
}
