package com.leonardo.tde.domain.model_assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.leonardo.tde.domain.Pagamento;
import com.leonardo.tde.resource.PagamentoResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PagamentoModelAssembler implements RepresentationModelAssembler<Pagamento, EntityModel<Pagamento>> {

    @Override
    public EntityModel<Pagamento> toModel(Pagamento pagamento) {
        EntityModel<Pagamento> entityModelPagamento = EntityModel.of(pagamento);

        entityModelPagamento.add(linkTo(methodOn(PagamentoResource.class).obterPagamentoPorId(pagamento.getId())).withSelfRel());
        entityModelPagamento.add(linkTo(methodOn(PagamentoResource.class).obterTodosPagamentos()).withRel(IanaLinkRelations.COLLECTION));

        return entityModelPagamento;
    }
}
