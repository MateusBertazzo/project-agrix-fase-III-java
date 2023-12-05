package com.betrybe.agrix.advice;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller exception advice.
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

  @ExceptionHandler(FarmNotFoundException.class)
    public ResponseEntity<String> farmNotFoundHandler(FarmNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(CropNotFoundException.class)
    public ResponseEntity<String> cropNotFoundHandler(CropNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(FertilizerNotFoundException.class)
    public ResponseEntity<String> fertilizerNotFoundHandler(FertilizerNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }
}
