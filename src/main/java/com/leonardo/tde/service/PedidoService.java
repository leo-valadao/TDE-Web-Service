package com.leonardo.tde.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.repository.PedidoRepository;

@Service
public class PedidoService {
            
    @Autowired
    private PedidoRepository pedidoRepository;
    
}
