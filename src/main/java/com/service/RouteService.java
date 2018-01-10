package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Route;

@Service
public interface RouteService {

    public List<Route> findAll();
    void remove(Route route);
    Route create(Route route);

    void updateRoute(Route currentRoute);
}
