package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Package;
import com.model.Route;
import com.model.User;

@Service
public interface PackageService {

    List<Package> findAll();
    List<Package> findAllByUser(User user);
    List<Package> findAllByName(String name);
    List<Package> findAllByRoute(Route route);
    Package create(Package pack);
    void remove(Package pack);

    void updatePackage(Package pack);
}
