package com.example.TodoListWithSecurity.model;

import com.example.TodoListWithSecurity.model.enums.UserRole;
import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username ;

    private String password;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
