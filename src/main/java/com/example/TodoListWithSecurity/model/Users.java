package com.example.TodoListWithSecurity.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class Users {
    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;


    private String username ;

    private String password;

    private Boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER )
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name="users_id")},
    inverseJoinColumns = {@JoinColumn(name="roles_id")})
    private List<Roles> rolesList;


    @JsonCreator
    public Users (@JsonProperty("usersId") Long usersId ) {
        this.usersId = usersId;
    }

}
