package com.service;


import com.model.Warehouse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WarehouseService {

    Warehouse create(Warehouse warehouse);
    void remove(Warehouse warehouse);
    public List<Warehouse> findAll();
}
