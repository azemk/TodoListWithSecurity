package com.example.TodoListWithSecurity.dto;


import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String username;
    private String password;
    private Boolean isActive;
    private String role;
}
