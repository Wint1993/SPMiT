package com.service;

import com.model.Transport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransportService {

    Transport create(Transport transport);
    void remove(Transport transport);
    List<Transport> findAll();
}
