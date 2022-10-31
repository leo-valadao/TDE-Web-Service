package com.leonardo.tde.domain.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.leonardo.tde.domain.Cidade;
import com.leonardo.tde.resource.CidadeResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CidadeModelAssembler implements RepresentationModelAssembler<Cidade, EntityModel<Cidade>> {

    @Override
    public EntityModel<Cidade> toModel(Cidade cidade) {
        EntityModel<Cidade> entityModelCidade = EntityModel.of(cidade);
        
        entityModelCidade.add(linkTo(methodOn(CidadeResource.class).obterCidadePorId(cidade.getId())).withSelfRel());
        entityModelCidade.add(linkTo(methodOn(CidadeResource.class).atualizarEstadoDaCidade(cidade.getId(),null)).withRel("estado"));
        entityModelCidade.add(linkTo(methodOn(CidadeResource.class).obterTodasCidades()).withRel(IanaLinkRelations.COLLECTION));

        return entityModelCidade;
    }
}
