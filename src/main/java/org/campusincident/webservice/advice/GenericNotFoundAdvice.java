//package org.campusincident.webservice.advice;
//
//import org.campusincident.webservice.exceptions.IncidentNotFoundException;
//import org.campusincident.webservice.exceptions.LocationNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ControllerAdvice
//public class GenericNotFoundAdvice {
//
//  @ResponseBody
//  @ExceptionHandler(value = {IncidentNotFoundException.class, LocationNotFoundException.class})
//  @ResponseStatus(HttpStatus.NOT_FOUND)
//  String employeeNotFoundHandler(Exception ex) {
//    return ex.getMessage();
//  }
//}