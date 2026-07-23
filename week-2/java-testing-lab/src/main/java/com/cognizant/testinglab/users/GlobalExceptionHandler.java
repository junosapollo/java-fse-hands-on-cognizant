package com.cognizant.testinglab.users;

import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<String> handleNotFound(RuntimeException exception) {
        return ResponseEntity.status(404).body("User not found");
    }
}
