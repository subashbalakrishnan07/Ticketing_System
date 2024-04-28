package com.Assignment.TicketingSystem.ExceptionHandlers;

import com.mongodb.DuplicateKeyException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<List<String>> handleException(WebExchangeBindException e) {
        var errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleException(UserNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<HashMap<String,String>> handleException(DuplicateKeyException e) {
        HashMap<String,String> error=new HashMap<>();
        error.put("Message",e.getMessage());
        error.put("Status", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<HashMap<String,String>> handleException(CustomException e) {
        HashMap<String,String>error=new HashMap<>();
        error.put("Message",e.getMessage());
        error.put("Status", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<HashMap<String,String>> handleException(TicketNotFoundException e) {
        HashMap<String,String>error=new HashMap<>();
        error.put("Message",e.getMessage());
        error.put("Status", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.badRequest().body(error);
    }


}
