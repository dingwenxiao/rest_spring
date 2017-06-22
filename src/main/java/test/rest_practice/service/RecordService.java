package test.rest_practice.service;

import java.util.List;

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


  public boolean isPalindrome(String s) {
    if (s.isEmpty()) {
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
