package com.example.TodoListWithSecurity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 5)
    private String task_name;

    @NotBlank
    @Size(min=5)
    private LocalDateTime dateTime;

    private String description;

    @ManyToOne
    @JoinColumn(name="users")
    private Users users;

    @JsonProperty("users")
    private void unpackNested(Long usersId) {
        this.users = new Users();
        users.setUsersId(usersId);
        users.setUsername(users.getUsername());
    }

}
