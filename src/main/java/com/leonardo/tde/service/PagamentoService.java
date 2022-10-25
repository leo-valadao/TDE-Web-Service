package com.leonardo.tde.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.repository.PagamentoRepository;

@Service
public class PagamentoService {
            
    @Autowired
    private PagamentoRepository pagamentoRepository;
    
}
