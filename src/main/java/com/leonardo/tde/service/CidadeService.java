package com.leonardo.tde.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.repository.CidadeRepository;

@Service
public class CidadeService {
        
    @Autowired
    private CidadeRepository cidadeRepository;
    
}
