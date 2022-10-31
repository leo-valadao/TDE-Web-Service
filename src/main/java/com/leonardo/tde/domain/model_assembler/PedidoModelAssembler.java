package com.leonardo.tde.domain.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.leonardo.tde.domain.Pedido;
import com.leonardo.tde.resource.PedidoResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        EntityModel<Pedido> entityModelPedido = EntityModel.of(pedido);

        entityModelPedido.add(linkTo(methodOn(PedidoResource.class).obterPedidoPorId(pedido.getId())).withSelfRel());
        entityModelPedido.add(linkTo(methodOn(PedidoResource.class).obterTodosPedidos()).withRel(IanaLinkRelations.COLLECTION));

        return entityModelPedido;
    }
}
