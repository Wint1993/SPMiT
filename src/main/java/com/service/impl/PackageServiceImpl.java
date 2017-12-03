package com.service.impl;

import com.model.Package;
import com.model.Route;
import com.model.User;
import com.model.Warehouse;
import com.repository.PackageRepository;
import com.repository.WarehouseRepository;
import com.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService{

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Package> findAll() {
        return packageRepository.findAll();
    }

    @Override
    public List<Package> findAllByUser(User user) {
        return packageRepository.findAllByUser(user);
    }

    @Override
    public List<Package> findAllByName(String name){
        return packageRepository.findAllByName(name);
    }

    @Override
    public List<Package> findAllByRoute(Route route) {
        return packageRepository.findAllByRoute(route);
    }

    @Override
    public Package create(Package pack){

        //String date = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
       // pack.setWhenTake(dateTime);

        pack.setCapacity(pack.getxDimension()*pack.getzDimension()*pack.getyDimension());
        pack.setTimeString(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        return packageRepository.save(pack);
    }

    @Override
    public void remove(Package pack) {
        packageRepository.delete(pack);
    }

    public void removeAllByUser(User user){


    }

    @Override
    public void updatePackage(Package pack){
        packageRepository.save(pack);
    }


}
