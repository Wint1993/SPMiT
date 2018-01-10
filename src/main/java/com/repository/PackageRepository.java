package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Package;
import com.model.Route;
import com.model.User;
import com.model.Warehouse;

public interface PackageRepository extends JpaRepository<Package, Long> {

    List<Package> findAllByUser(User user);
    List<Package> findAllByName(String name);
    List<Package> findAllByRoute(Route route);
    List<Package> findAllByWarehouse(Warehouse warehouse);
    Package findOneById(Long id);
    List<Package> findAllByWarehouseId(long id);
    List<Package> findAllByRouteId(long id);
}
