package com.leonardo.tde.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.repository.TelefoneRepository;

@Service
public class TelefoneService {
        
    @Autowired
    private TelefoneRepository telefoneRepository;
    
}
