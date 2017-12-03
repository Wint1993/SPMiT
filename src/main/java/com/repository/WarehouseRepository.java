package com.repository;

import com.model.User;
import com.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findAll();
    List<Warehouse> findAllByAddress(String address);
    List<Warehouse> findAllByName(String name);
    Warehouse findOneById(Long id);



}
