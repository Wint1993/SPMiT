package com.repository;

import com.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

     List<Route> findAll();
     Route findOneById(Long id);

}
