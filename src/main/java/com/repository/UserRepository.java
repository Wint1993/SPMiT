package com.repository;

import com.model.Package;
import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findOneByFirstName(String name);
    User findOneByLastName(String name);
    User findOneById(Long id);

}
