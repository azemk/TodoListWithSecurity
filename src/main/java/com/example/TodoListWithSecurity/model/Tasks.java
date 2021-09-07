package com.example.TodoListWithSecurity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

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
    private Long id;

    private String task_name;

    private LocalDateTime dateTime;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "users")
    private Users users;

    @JsonProperty("users")
    private void unpackNested(Long usersId) {
        this.users = new Users();
        users.setUsersId(usersId);
        users.setUsername(users.getUsername());
    }



}
