package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findOneByFirstName(String name);
    User findOneByLastName(String name);
    User findOneById(Long id);

}
