package com.controller;

import java.util.List;

import com.sun.xml.internal.bind.v2.schemagen.episode.Package;
import com.sun.xml.internal.ws.addressing.W3CAddressingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Transport;
import com.repository.TransportRepository;
import com.service.TransportService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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

    @RequestMapping(value = "/edit/{id}", method = PUT)
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody Transport transport){

        Transport currentTransport = transportRepository.findOneById(id);
        currentTransport.setTransportName(transport.getTransportName());
        currentTransport.setMaxWeight(transport.getMaxWeight());
        currentTransport.setMaxCapacity(transport.getMaxCapacity());
        currentTransport.setxDimension(transport.getxDimension());
        currentTransport.setyDimension(transport.getyDimension());
        currentTransport.setzDimension(transport.getzDimension());
        currentTransport.setDriverFirstName(transport.getDriverLastName());
        currentTransport.setDriverLastName(transport.getDriverLastName());
        currentTransport.setDriverTelephoneName(transport.getDriverTelephoneName());
        transportService.updateTransport(currentTransport);

        return new ResponseEntity<Transport>(currentTransport, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public Transport findOne(@PathVariable Long id) {

        return transportRepository.findOne(id);
    }

}
