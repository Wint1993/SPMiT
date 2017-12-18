package com.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Transport;
import com.repository.TransportRepository;
import com.service.TransportService;

@RestController
@RequestMapping("/api/transport")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @Autowired
    private TransportRepository transportRepository;

    @RequestMapping(value = "/create", method = POST)
    public Transport create(@RequestBody Transport transport){
        return transportService.create(transport);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<Transport> findAll(){
        return transportService.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = DELETE)
    public Transport remove(@PathVariable long id){

        Transport transport = transportRepository.findOneById(id);
        transportService.remove(transport);
        transportRepository.flush();
        return transport;
    }
}
