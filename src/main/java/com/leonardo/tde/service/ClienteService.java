package com.leonardo.tde.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.repository.ClienteRepository;

@Service
public class ClienteService {
            
    @Autowired
    private ClienteRepository clienteRepository;
    
}
