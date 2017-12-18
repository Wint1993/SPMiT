package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Transport;

public interface TransportRepository extends JpaRepository<Transport, Long> {

    List<Transport> findAll();
    Transport findOneById(Long id);

}
