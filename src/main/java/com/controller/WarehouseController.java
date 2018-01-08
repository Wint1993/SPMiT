package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Package;
import com.model.Warehouse;
import com.repository.PackageRepository;
import com.repository.WarehouseRepository;
import com.service.WarehouseService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/warehouse")

public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private PackageRepository packageRepository;

    @RequestMapping(value = "/create", method = POST)
    public Warehouse create(@RequestBody Warehouse warehouse){
        return warehouseService.create(warehouse);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<Warehouse> findAll(){
        return warehouseService.findAll();
    }
    @RequestMapping(value = "/edit/{id}", method = PUT)
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody Warehouse warehouse){

        Warehouse currentWarehouse = warehouseRepository.findOneById(id);
        currentWarehouse.setName(warehouse.getName());
        currentWarehouse.setAddress(warehouse.getAddress());
        currentWarehouse.setTelephoneNumber(warehouse.getTelephoneNumber());
        warehouseService.updateWarehouse(currentWarehouse);

        return new ResponseEntity<Warehouse>(currentWarehouse, HttpStatus.OK);
    }


    @RequestMapping(value = "/all/{id}", method = GET)
    public List<Package> findAllIn(@PathVariable("id") Long id){
        return packageRepository.findAllByWarehouseId(id);
    }

    @RequestMapping(value = "/remove/{id}", method = DELETE)
    public Warehouse remove(@PathVariable long id){

        Warehouse warehouse = warehouseRepository.findOneById(id);
        warehouseService.remove(warehouse);
        warehouseRepository.flush();
        return warehouse;
    }

    @GetMapping(value = "/{id}")
    public Warehouse findOne(@PathVariable Long id) {

        return warehouseRepository.findOne(id);
    }

}
