package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Warehouse;

@Service
public interface WarehouseService {

    Warehouse create(Warehouse warehouse);
    void remove(Warehouse warehouse);
    public List<Warehouse> findAll();
    void updateWarehouse(Warehouse warehouse);
}
