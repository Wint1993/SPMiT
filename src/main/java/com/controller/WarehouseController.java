package com.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Package;
import com.model.Warehouse;
import com.repository.PackageRepository;
import com.repository.WarehouseRepository;
import com.service.WarehouseService;

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

    @RequestMapping(value = "/all/{id}", method = GET)
    public List<Package> findAllIn(@PathVariable long id){
        return packageRepository.findAllByWarehouseId(id);
    }

    @RequestMapping(value = "/remove/{id}", method = DELETE)
    public Warehouse remove(@PathVariable long id){

        Warehouse warehouse = warehouseRepository.findOneById(id);
        warehouseService.remove(warehouse);
        warehouseRepository.flush();
        return warehouse;
    }



}
