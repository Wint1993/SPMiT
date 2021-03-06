package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Package;
import com.model.Warehouse;
import com.repository.PackageRepository;
import com.repository.WarehouseRepository;
import com.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {


    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public Warehouse create(Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public void remove(Warehouse warehouse) {

        if(packageRepository.findAllByWarehouse(warehouse).isEmpty()){
            warehouseRepository.delete(warehouse);
        } else{
            for (Package pack: packageRepository.findAllByWarehouse(warehouse)) {
                packageRepository.delete(pack);
                packageRepository.flush();
                warehouseRepository.delete(warehouse);
            }
        }

        warehouseRepository.delete(warehouse);
    }


    @Override
    public void updateWarehouse(Warehouse warehouse){
        warehouseRepository.save(warehouse);
    }

}
