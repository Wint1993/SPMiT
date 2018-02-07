package com.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.model.Package;
import com.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Route;
import com.repository.RouteRepository;
import com.service.RouteService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
	private PackagingOptimisationService packagingOptimisationService;

    @RequestMapping(value = "/create")
    public ResponseEntity<List<Route>> create(@RequestBody Route route){

        warehousesEqualsValidation(route);
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

    @PostMapping(path= "/optimise")
	public Route optimise(@RequestBody InputDto inputDto) {

    	return packagingOptimisationService.optimise(inputDto);
	}

    @RequestMapping(value = "/edit/{id}", method = PUT)
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody Route route){

        Route currentRoute = routeRepository.findOneById(id);
        currentRoute.setWarehouseStart(route.getWarehouseStart());
        currentRoute.setWarehouseEnd(route.getWarehouseEnd());
        currentRoute.setDescription(route.getDescription());
        currentRoute.setStartRoute(route.getStartRoute());
        currentRoute.setEndRoute(route.getEndRoute());
        currentRoute.setTransport(route.getTransport());
        currentRoute.setPackages(route.getPackages());



        routeService.updateRoute(currentRoute);

        return new ResponseEntity<Route>(currentRoute, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public Route findOne(@PathVariable Long id) {

        return routeRepository.findOne(id);
    }

    @RequestMapping(value = "/all/{id}", method = GET)
    public List<Package> findAllIn(@PathVariable("id") Long id){
        return packageRepository.findAllByRouteId(id);
    }

    public ResponseEntity<Route> warehousesEqualsValidation( Route route) {
        if(route.getWarehouseStart().getId().equals(route.getWarehouseEnd().getId())) {
            return new ResponseEntity<>(route, HttpStatus.NOT_ACCEPTABLE);
        }
        return null;
    }
}
