package test.rest_practice.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Record {

  private String value;
  private Boolean isPalindrome;
  
  @JsonFormat
  (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Date date;
  
  public Record() {}

  public Record(String value, Boolean isPalindrome, Date date) {
    this.value = value;
    this.isPalindrome = isPalindrome;
    this.date = date;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Boolean getIsPalindrome() {
    return isPalindrome;
  }

  public void setIsPalindrome(Boolean isPalindrome) {
    this.isPalindrome = isPalindrome;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Record record = (Record) o;
      return value == record.value;
  }

  @Override
  public int hashCode() {
      return Objects.hash(value, isPalindrome);
  }
  
}
