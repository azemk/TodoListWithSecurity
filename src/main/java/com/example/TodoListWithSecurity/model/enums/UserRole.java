package com.example.TodoListWithSecurity.model.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.TodoListWithSecurity.model.enums.UserAuthorities.READ;
import static com.example.TodoListWithSecurity.model.enums.UserAuthorities.WRITE;

public enum UserRole {
    USER(Sets.newHashSet(READ)),
    ADMIN(Sets.newHashSet(READ,WRITE));

    private final Set<UserAuthorities> permissions;
    UserRole(Set<UserAuthorities> permissions) {
        this.permissions = permissions;
    }

    public Set<UserAuthorities> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
