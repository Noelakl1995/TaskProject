package com.example.activity.User;

import static com.example.activity.User.Permission.ADMIN_CREATE;
import static com.example.activity.User.Permission.ADMIN_DELETE;
import static com.example.activity.User.Permission.ADMIN_READ;
import static com.example.activity.User.Permission.ADMIN_UPDATE;
import static com.example.activity.User.Permission.MANAGER_CREATE;
import static com.example.activity.User.Permission.MANAGER_DELETE;
import static com.example.activity.User.Permission.MANAGER_READ;
import static com.example.activity.User.Permission.MANAGER_UPDATE;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.springframework.security.core.authority.SimpleGrantedAuthority;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public enum UserRole {
    USER(Collections.emptySet()),

    ADMIN(Set.of(ADMIN_READ,
    ADMIN_UPDATE,
    ADMIN_DELETE,
    ADMIN_CREATE,
    MANAGER_READ,
    MANAGER_UPDATE,
    MANAGER_DELETE,
    MANAGER_CREATE)),

    MANAGER(Set.of(ADMIN_READ,
    MANAGER_READ,
    MANAGER_UPDATE,
    MANAGER_DELETE,
    MANAGER_CREATE));

   

    private Set<String> roles;


    
    @Getter
    private final Set<Permission> permission;

   

    public List<SimpleGrantedAuthority> getAuthority(){
        var authorities = getPermission()
        .stream()
        .map(permission -> new SimpleGrantedAuthority(permission.name()))
        .toList();
        authorities.add(new SimpleGrantedAuthority("ROLE_" +this.name()));
        
        return authorities;

    }
}
 