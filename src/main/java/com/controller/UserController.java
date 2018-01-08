package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.User;
import com.repository.PackageRepository;
import com.repository.UserRepository;
import com.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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

    @RequestMapping(value = "/edit/{id}", method = PUT)
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody User user){

        User currentUser = userRepository.findOneById(id);
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setAddress(user.getAddress());
        currentUser.setFirmName(user.getFirmName());
        currentUser.setTelephoneNumber(user.getTelephoneNumber());
        currentUser.setEmail(user.getEmail());
        userService.updateUser(currentUser);

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public User findOne(@PathVariable Long id) {

        return userRepository.findOne(id);
    }


}
