package com.leonardo.tde.domain.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.leonardo.tde.domain.Cliente;
import com.leonardo.tde.resource.ClienteResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {
        EntityModel<Cliente> entityModelCliente = EntityModel.of(cliente);
        
        entityModelCliente.add(linkTo(methodOn(ClienteResource.class).obterClientePorId(cliente.getId())).withSelfRel());
        entityModelCliente.add(linkTo(methodOn(ClienteResource.class).atualizarEnderecosDoCliente(cliente.getId(),null)).withRel("endereços"));
        entityModelCliente.add(linkTo(methodOn(ClienteResource.class).obterTodosClientes()).withRel(IanaLinkRelations.COLLECTION));

        return entityModelCliente;
    }
}
