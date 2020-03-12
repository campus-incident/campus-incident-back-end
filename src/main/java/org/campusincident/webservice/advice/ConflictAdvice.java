package org.campusincident.webservice.advice;

import org.campusincident.webservice.exceptions.CategoryNameAlreadyExsistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ConflictAdvice {

  @ResponseBody
  @ExceptionHandler(value = {
		  CategoryNameAlreadyExsistException.class
  })
  @ResponseStatus(HttpStatus.CONFLICT)
  String employeeNotFoundHandler(Exception ex) {
    return ex.getMessage();
  }
}
