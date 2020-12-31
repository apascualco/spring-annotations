package com.apascualco.annotations.service;

import com.apascualco.annotations.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService {

    private List<User> users;

    public List<User> activeAllUsers() {
        System.out.println("Join activeAllUsers");
        return getAllUsers().stream()
                .peek(user -> user.setActive(true))
                .collect(Collectors.toList());
    }

    @Cacheable("users")
    public List<User> getAllUsers() {
        System.out.println("Join getAllUsers");
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
