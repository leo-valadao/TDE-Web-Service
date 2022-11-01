package com.leonardo.tde.domain.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.leonardo.tde.domain.Estado;
import com.leonardo.tde.resource.EstadoResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class EstadoModelAssembler implements RepresentationModelAssembler<Estado, EntityModel<Estado>> {
    
    @Override
    public EntityModel<Estado> toModel(Estado estado) {
        EntityModel<Estado> entityModelEstado = EntityModel.of(estado);
        
        entityModelEstado.add(linkTo(methodOn(EstadoResource.class).obterEstadoPorId(estado.getId())).withSelfRel());
        entityModelEstado.add(linkTo(methodOn(EstadoResource.class).obterTodosEstados()).withRel(IanaLinkRelations.COLLECTION));
        // entityModelEstado.add(linkTo(methodOn(EstadoResource.class).atualizarCidadesDoEstado(estado.getId(),null)).withRel("estado"));

        return entityModelEstado;
    }  
}
