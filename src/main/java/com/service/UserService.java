package com.service;


import com.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User create(User user);
    void remove(User user);
    List<User> findAll();
}
