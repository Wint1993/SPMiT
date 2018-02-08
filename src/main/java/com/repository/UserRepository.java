package com.repository;

import com.model.Package;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.User;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findOneByFirstName(String name);
    User findOneByLastName(String name);
    User findOneById(Long id);


}
