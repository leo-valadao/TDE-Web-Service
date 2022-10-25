package com.leonardo.tde.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.tde.repository.ProdutoRepository;

@Service
public class ProdutoService {
            
    @Autowired
    private ProdutoRepository produtoRepository;
    
}
