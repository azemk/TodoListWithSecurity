package com.example.TodoListWithSecurity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private String taskName;
    private String description;
    private String username;
}
