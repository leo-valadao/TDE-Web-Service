package com.leonardo.tde.domain.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.leonardo.tde.domain.Produto;
import com.leonardo.tde.resource.ProdutoResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ProdutoModelAssembler implements RepresentationModelAssembler<Produto, EntityModel<Produto>> {
    
    @Override
    public EntityModel<Produto> toModel(Produto produto) {
        EntityModel<Produto> entityModelProduto = EntityModel.of(produto);

        entityModelProduto.add(linkTo(methodOn(ProdutoResource.class).obterProdutoPorId(produto.getId())).withSelfRel());
        entityModelProduto.add(linkTo(methodOn(ProdutoResource.class).atualizarCategoriasDoProduto(produto.getId(), null)).withRel("categorias"));
        entityModelProduto.add(linkTo(methodOn(ProdutoResource.class).obterTodosProdutos()).withRel(IanaLinkRelations.COLLECTION));

        return entityModelProduto;
    }
}
