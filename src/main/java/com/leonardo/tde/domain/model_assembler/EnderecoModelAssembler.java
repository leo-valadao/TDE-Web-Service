package com.leonardo.tde.domain.model_assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.leonardo.tde.domain.Endereco;
import com.leonardo.tde.resource.EnderecoResource;

public class EnderecoModelAssembler implements RepresentationModelAssembler<Endereco, EntityModel<Endereco>> {

    @Override
    public EntityModel<Endereco> toModel(Endereco endereco) {
        EntityModel<Endereco> entityModelEndereco = EntityModel.of(endereco);
        
        entityModelEndereco.add(linkTo(methodOn(EnderecoResource.class).obterEnderecoPorId(endereco.getId())).withSelfRel());
        entityModelEndereco.add(linkTo(methodOn(EnderecoResource.class).atualizarClienteDoEndereco(endereco.getId(),null)).withRel("endereços"));
        entityModelEndereco.add(linkTo(methodOn(EnderecoResource.class).obterTodosEnderecos()).withRel(IanaLinkRelations.COLLECTION));

        return entityModelEndereco;
    }    
}
