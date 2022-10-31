package com.leonardo.tde.domain.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.leonardo.tde.domain.Categoria;
import com.leonardo.tde.resource.CategoriaResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CategoriaModelAssembler implements RepresentationModelAssembler<Categoria, EntityModel<Categoria>> {

    @Override
    public EntityModel<Categoria> toModel(Categoria categoria) {
        EntityModel<Categoria> entityModelCategoria = EntityModel.of(categoria);

        entityModelCategoria.add(linkTo(methodOn(CategoriaResource.class).obterCategoriaPorId(categoria.getId())).withSelfRel());
        entityModelCategoria.add(linkTo(methodOn(CategoriaResource.class).atualizarProdutosDaCategoria(categoria.getId(), null)).withRel("produtos"));
        entityModelCategoria.add(linkTo(methodOn(CategoriaResource.class).obterTodasCategorias()).withRel(IanaLinkRelations.COLLECTION));

        return entityModelCategoria;
    }
}
