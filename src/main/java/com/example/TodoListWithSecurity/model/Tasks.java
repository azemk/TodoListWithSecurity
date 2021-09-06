package com.example.TodoListWithSecurity.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tasks" )
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String task_name;

    private String description;

    private LocalDateTime task_date;

    @ManyToOne
    @JoinColumn(name = "users")
    private Users users;


}
