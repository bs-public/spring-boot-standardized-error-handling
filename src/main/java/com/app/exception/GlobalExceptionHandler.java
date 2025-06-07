package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ProblemDetail handleUserNotFound(UserNotFoundException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problemDetail.setTitle("User Not Found");
    problemDetail.setDetail(ex.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(DuplicateEmailException.class)
  public ProblemDetail handleDuplicateEmail(DuplicateEmailException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    problemDetail.setTitle("Duplicate Email");
    problemDetail.setDetail(ex.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    problemDetail.setTitle("Validation Failed");
    problemDetail.setDetail("Request has validation errors.");

    problemDetail.setProperty(
        "errors",
        ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
            .toArray());
    return problemDetail;
  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleOtherExceptions(Exception ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    problemDetail.setTitle("Unexpected Error");
    problemDetail.setDetail(ex.getMessage());
    return problemDetail;
  }
}
