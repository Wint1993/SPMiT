package com.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Package;
import com.model.Route;
import com.model.Transport;
import com.repository.RouteRepository;
import com.service.RouteService;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
	private PackagingOptimisationService packagingOptimisationService;

    @RequestMapping(value = "/create", method = POST)
    public Route create(@RequestBody Route route){
        return routeService.create(route);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<Route> findAll(){
        return routeService.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = DELETE)
    public Route remove(@PathVariable long id){

        Route route = routeRepository.findOneById(id);
        routeService.remove(route);
        routeRepository.flush();
        return route;
    }

    @GetMapping("/optimise")
	public TransportPackagingDto optimise(@RequestBody Transport transport, @RequestBody List<Package> packages) {

    	return packagingOptimisationService.optimise(transport, packages);
	}
}
