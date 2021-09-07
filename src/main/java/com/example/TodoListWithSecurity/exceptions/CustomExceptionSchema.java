package com.example.TodoListWithSecurity.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;




@Data
@AllArgsConstructor
@Builder
public class CustomExceptionSchema {
    private String message;
}
