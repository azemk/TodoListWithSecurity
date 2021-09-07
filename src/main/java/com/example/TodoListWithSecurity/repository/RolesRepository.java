package com.example.TodoListWithSecurity.repository;

import com.example.TodoListWithSecurity.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findRolesByName(String name);

}
