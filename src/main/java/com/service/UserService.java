package com.service;

import java.util.List;

import com.model.Warehouse;
import org.springframework.stereotype.Service;

import com.model.User;

@Service
public interface UserService {
    User create(User user);
    void remove(User user);
    List<User> findAll();
    void updateUser(User user);
}
