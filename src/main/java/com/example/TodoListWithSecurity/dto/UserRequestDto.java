package com.example.TodoListWithSecurity.dto;




import lombok.*;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.lang.NonNullFields;


import javax.persistence.Column;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank
    @Size(min = 1 , max = 8)
    @Column(nullable = false )
    private String username;
    @NotBlank
    @Size(min = 1 , max = 8)
    @Column(nullable = false)
    private String password;
}
