package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Route;
import com.repository.PackageRepository;
import com.repository.RouteRepository;
import com.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
	private PackageRepository packageRepository;

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route create(Route route){

		routeRepository.save(route);

		route.getPackages().forEach(p -> {
			p.setRoute(route);
			packageRepository.save(p);
		});

		return route;
    }

    @Override
    public void remove(Route route) {
        routeRepository.delete(route);
    }
}
