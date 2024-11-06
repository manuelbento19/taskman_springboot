package com.bentoo.taskman.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AppError.class)
    public ResponseEntity handlerException(AppError error){
        Map<String,String> response = new HashMap<String,String>();
        response.put("message",error.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}

