package com.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Route> create(Route route){

        String start = changeDataStartViewFormat(route);
        route.setStartRoute(start);

        String end = changeDataEndViewFormat(route);
        route.setEndRoute(end);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(start, formatter);
        LocalDateTime dateTime1 = LocalDateTime.parse(end, formatter);
        route.setStartRoute1(dateTime);
        route.setEndRoute1(dateTime1);

        LocalDateTime s1 = route.getStartRoute1();
        LocalDateTime s2 = route.getEndRoute1();

        if(route.getPackages().isEmpty()){
            return new ResponseEntity<>(route, HttpStatus.NOT_ACCEPTABLE);
        }

        if (ifStartDateIsAfterEndDate(s1, s2)) return new ResponseEntity<>(route, HttpStatus.NOT_ACCEPTABLE);

        ResponseEntity<Route> rout = transportTimeValidation1(route, s1, s2);
        if (rout != null) return rout;

        routeRepository.save(route);
		route.getPackages().forEach(p -> {
			p.setRoute(route);
			packageRepository.save(p);
		});

        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @Override
    public void updateRoute(Route route){
        routeRepository.save(route);
    }

    @Override
    public void remove(Route route) {
        routeRepository.delete(route);
    }

    private boolean ifStartDateIsAfterEndDate(LocalDateTime s1, LocalDateTime s2) {
        if(s1.isAfter(s2)){
            return true;
        }
        return false;
    }

    private ResponseEntity<Route> transportTimeValidation1(Route route, LocalDateTime s1, LocalDateTime s2) {
        List<Route> routes = routeRepository.findAllByTransportId(route.getTransport().getId());
        for (Route rout:routes) {

            LocalDateTime start1 = rout.getStartRoute1();
            LocalDateTime end2 = rout.getEndRoute1();

            // nie zwraca odpowiedniego routa- tzn tego który został zlapany przez pętle !
            if(!(s1.isAfter(end2) && s2.isAfter(end2) || s1.isBefore(start1) && s2.isBefore(start1))){
                return new ResponseEntity<Route>(rout, HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return null;
    }


    public String changeDataStartViewFormat(Route route){
        String inputDateStart = route.getStartRoute();
        String datePart1Start = inputDateStart.substring(0,10);
        String datePart2Start = inputDateStart.substring(11,19);
        return datePart1Start + " " + datePart2Start;

    }

    public String changeDataEndViewFormat(Route route){
        String inputDateEnd = route.getEndRoute();
        String datePart1End = inputDateEnd.substring(0,10);
        String datePart2End = inputDateEnd.substring(11,19);
        return datePart1End + " " + datePart2End;
    }
}
