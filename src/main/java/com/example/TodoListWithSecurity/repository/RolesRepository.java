package com.example.TodoListWithSecurity.repository;

import com.example.TodoListWithSecurity.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RolesRepository extends JpaRepository<Roles,Long> {
    List<Roles> findRolesByName(String name);
    Roles findRoleByName(String name);


}
