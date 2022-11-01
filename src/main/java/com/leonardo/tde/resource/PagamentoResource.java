package com.leonardo.tde.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.tde.domain.Pagamento;
import com.leonardo.tde.domain.model_assembler.PagamentoModelAssembler;
import com.leonardo.tde.service.PagamentoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// API do Pagamento - Versão 1
@RestController
@RequestMapping("/api/v1/pagamento")
public class PagamentoResource {

    // Objetos
    // Serviço do Pagamento
    @Autowired
    private PagamentoService pagamentoService;

    // Montador de Modelo do Pagamento
    // @Autowired
    private PagamentoModelAssembler pagamentoModelAssembler = new PagamentoModelAssembler();

    // API's
    // Obters Todos os Pagamentos
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pagamento>>> obterTodosPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.todosPagamentos();

        if (!pagamentos.isEmpty()) {
            List<EntityModel<Pagamento>> listaEntityModelPagamentos = new ArrayList<EntityModel<Pagamento>>();

            listaEntityModelPagamentos.addAll(pagamentos.stream().map(pagamentoModelAssembler::toModel).toList());

            CollectionModel<EntityModel<Pagamento>> collectionModel;
            collectionModel = CollectionModel.of(listaEntityModelPagamentos);

            return ResponseEntity.ok(collectionModel);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obter Pagamento Por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pagamento>> obterPagamentoPorId(@PathVariable("id") Integer id) {
        Pagamento pagamento = pagamentoService.pagamentoPorId(id);

        EntityModel<Pagamento> entityModelPagamento = pagamentoModelAssembler.toModel(pagamento);

        return ResponseEntity.ok(entityModelPagamento);
    }

    // Salvar a Pagamento
    @PostMapping
    public ResponseEntity<EntityModel<Pagamento>> salvarPagamento(@RequestBody @Valid Pagamento pagamento,
            HttpServletRequest request) {
        pagamentoService.salvarPagamento(pagamento);

        URI uri = linkTo(methodOn(PagamentoResource.class).obterPagamentoPorId(pagamento.getId())).withSelfRel()
                .toUri();
        EntityModel<Pagamento> entityModelPagamento = pagamentoModelAssembler.toModel(pagamento);

        return ResponseEntity.created(uri).body(entityModelPagamento);
    }

    // Atualizar a Pagamento
    @PutMapping
    public ResponseEntity<EntityModel<Pagamento>> atualizarPagamento(@RequestBody Pagamento pagamento) {
        pagamentoService.pagamentoPorId(pagamento.getId());
        pagamentoService.atualizarPagamento(pagamento);

        EntityModel<Pagamento> entityModelPagamento = pagamentoModelAssembler.toModel(pagamento);

        return ResponseEntity.ok(entityModelPagamento);
    }

    // Excluir a Pagamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamentoPorId(@PathVariable("id") Integer id) {
        pagamentoService.excluirPagamento(id);

        return ResponseEntity.noContent().build();
    }

    // Atualizar os Cidades da Pagamento
    // @PatchMapping("/{id}/cidades")
    // public ResponseEntity<EntityModel<Pagamento>>
    // atualizarCidadesDoPagamento(@PathVariable("id") Integer idPagamento,
    // List<Cidade> cidades) {
    // Pagamento pagamento =
    // pagamentoService.atualizarCidadesDoPagamento(idPagamento, cidades);

    // EntityModel<Pagamento> entityModelPagamento =
    // pagamentoModelAssembler.toModel(pagamento);

    // return ResponseEntity.ok(entityModelPagamento);
    // }
}