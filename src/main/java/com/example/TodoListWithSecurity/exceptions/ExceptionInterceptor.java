package com.example.TodoListWithSecurity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@RestController
@ControllerAdvice
public class ExceptionInterceptor extends RuntimeException{

    @ExceptionHandler(CommonException.class)
    private ResponseEntity<Object> handleNotFoundException(CommonException ex, WebRequest webRequest){
        CustomExceptionSchema body = new CustomExceptionSchema(
                ex.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
