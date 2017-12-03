package com.service.impl;

import com.model.Package;
import com.model.User;
import com.repository.PackageRepository;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public User create(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public void remove(User user) {

        if(packageRepository.findAllByUser(user).isEmpty()){
            userRepository.delete(user);
        } else{
            for (Package pack: packageRepository.findAllByUser(user)) {
                packageRepository.delete(pack);
                packageRepository.flush();
                userRepository.delete(user);
            }
        }
    }
}
