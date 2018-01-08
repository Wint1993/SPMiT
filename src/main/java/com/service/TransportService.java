package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Transport;

@Service
public interface TransportService {

    Transport create(Transport transport);
    void remove(Transport transport);
    List<Transport> findAll();
    void updateTransport(Transport transport);
}
