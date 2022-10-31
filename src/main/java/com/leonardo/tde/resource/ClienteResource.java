package com.leonardo.tde.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.tde.service.ClienteService;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteResource {
    
    // Objetos
    // Serviço do Cliente
    @Autowired
    private ClienteService clienteService;

    // Montador de Modelo do Cliente
    //@Autowired
    //private ClienteModelAssembler clienteModelAssembler;



}
