package com.service;

import com.model.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {

    public List<Route> findAll();
    void remove(Route route);
    Route create(Route route);
}
