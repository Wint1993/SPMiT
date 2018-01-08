package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findAll();
    List<Warehouse> findAllByAddress(String address);
    Warehouse findOneById(Long id);



}
