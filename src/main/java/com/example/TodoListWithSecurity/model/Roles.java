package com.example.TodoListWithSecurity.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Entity
@Table(name = "roles")
@Getter
@Setter
public class Roles {
    @Id
    @Column(name = "roles_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolesId;

    private String name;


}
