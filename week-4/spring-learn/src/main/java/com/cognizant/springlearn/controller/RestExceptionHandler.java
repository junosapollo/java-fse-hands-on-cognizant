package com.cognizant.springlearn.controller;

import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<Map<String,String>> notFound(NoSuchElementException e) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage())); }
}
