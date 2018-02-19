package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.model.Route;

@Service
public interface RouteService {

    List<Route> findAll();
    void remove(Route route);
    ResponseEntity<List<Route>> create(Route route);
    void changeRouteStatus(Long id);
    void updateRoute(Route currentRoute);
}
