package test.rest_practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="This record is not found in the system") 
public class RecordNotFoundException extends Exception {

}

