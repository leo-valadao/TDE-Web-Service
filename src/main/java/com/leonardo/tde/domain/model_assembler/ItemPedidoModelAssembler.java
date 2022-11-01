package com.leonardo.tde.domain.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.leonardo.tde.domain.ItemPedido;
import com.leonardo.tde.resource.ItemPedidoResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ItemPedidoModelAssembler implements RepresentationModelAssembler<ItemPedido, EntityModel<ItemPedido>> {

    @Override
    public EntityModel<ItemPedido> toModel(ItemPedido itemPedido) {
        EntityModel<ItemPedido> entityModelItemPedido = EntityModel.of(itemPedido);

        //entityModelItemPedido.add(linkTo(methodOn(ItemPedidoResource.class).obterItemPedidoPorId(itemPedido.getItemPedidoPK()))).withSelfRel();
        entityModelItemPedido.add(linkTo(methodOn(ItemPedidoResource.class).obterTodosItemPedidos()).withRel(IanaLinkRelations.COLLECTION));
        // entityModelItemPedido.add(linkTo(methodOn(ItemPedidoResource.class).atualizarCidadesDoItemPedido(itemPedido.getId(),null)).withRel("itemPedido"));

        return entityModelItemPedido;
    }
}
