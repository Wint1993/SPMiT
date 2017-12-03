package com.controller;

import com.model.User;
import com.repository.PackageRepository;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackageRepository packageRepository;


    @RequestMapping(value = "/create", method = POST)
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = DELETE)
    public User remove(@PathVariable long id){

        User user = userRepository.findOneById(id);

        userService.remove(user);
        userRepository.flush();
        packageRepository.flush();
        return user;
    }

}
