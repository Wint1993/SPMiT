package com.repository;

import com.model.Transport;
import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportRepository extends JpaRepository<Transport, Long> {

    List<Transport> findAll();
    Transport findOneById(Long id);

}
