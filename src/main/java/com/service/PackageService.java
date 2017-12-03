package com.service;

import com.model.Package;
import com.model.Route;
import com.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

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
