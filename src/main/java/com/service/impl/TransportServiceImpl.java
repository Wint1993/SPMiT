package com.service.impl;


import com.model.Transport;
import com.repository.TransportRepository;
import com.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public Transport create(Transport transport){
        return transportRepository.save(transport);
    }

    @Override
    public List<Transport> findAll() {
        return transportRepository.findAll();
    }

    @Override
    public void remove(Transport transport) {
        transportRepository.delete(transport);
    }
}
