package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

     List<Route> findAll();
     Route findOneById(Long id);

}
