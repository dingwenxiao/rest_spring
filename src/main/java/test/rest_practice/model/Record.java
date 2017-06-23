package test.rest_practice.model;

public class Record {

  private String id;
  private String value;
  private Boolean isPalindrome;

  public Record() {}

  public Record(String id, String value, Boolean isPalindrome) {
    this.id = id;
    this.value = value;
    this.isPalindrome = isPalindrome;
  }

  public Boolean getIsPalindrome() {
    return isPalindrome;
  }

  public void setIsPalindrome(Boolean isPalindrome) {
    this.isPalindrome = isPalindrome;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
