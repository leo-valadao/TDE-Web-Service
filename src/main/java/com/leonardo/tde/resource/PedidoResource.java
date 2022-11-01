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

import com.leonardo.tde.domain.Pedido;
import com.leonardo.tde.domain.model_assembler.PedidoModelAssembler;
import com.leonardo.tde.service.PedidoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// API do Pedido - Versão 1
@RestController
@RequestMapping("/api/v1/pedido")
public class PedidoResource {

    // Objetos
    // Serviço do Pedido
    @Autowired
    private PedidoService pedidoService;

    // Montador de Modelo do Pedido
    // @Autowired
    private PedidoModelAssembler pedidoModelAssembler = new PedidoModelAssembler();

    // API's
    // Obters Todos os Pedidos
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pedido>>> obterTodosPedidos() {
        List<Pedido> pedidos = pedidoService.todosPedidos();

        if (!pedidos.isEmpty()) {
            List<EntityModel<Pedido>> listaEntityModelPedidos = new ArrayList<EntityModel<Pedido>>();

            listaEntityModelPedidos.addAll(pedidos.stream().map(pedidoModelAssembler::toModel).toList());

            CollectionModel<EntityModel<Pedido>> collectionModel;
            collectionModel = CollectionModel.of(listaEntityModelPedidos);

            return ResponseEntity.ok(collectionModel);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obter Pedido Por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pedido>> obterPedidoPorId(@PathVariable("id") Integer id) {
        Pedido pedido = pedidoService.pedidoPorId(id);

        EntityModel<Pedido> entityModelPedido = pedidoModelAssembler.toModel(pedido);

        return ResponseEntity.ok(entityModelPedido);
    }

    // Salvar a Pedido
    @PostMapping
    public ResponseEntity<EntityModel<Pedido>> salvarPedido(@RequestBody @Valid Pedido pedido,
            HttpServletRequest request) {
        pedidoService.salvarPedido(pedido);

        URI uri = linkTo(methodOn(PedidoResource.class).obterPedidoPorId(pedido.getId())).withSelfRel().toUri();
        EntityModel<Pedido> entityModelPedido = pedidoModelAssembler.toModel(pedido);

        return ResponseEntity.created(uri).body(entityModelPedido);
    }

    // Atualizar a Pedido
    @PutMapping
    public ResponseEntity<EntityModel<Pedido>> atualizarPedido(@RequestBody Pedido pedido) {
        pedidoService.pedidoPorId(pedido.getId());
        pedidoService.atualizarPedido(pedido);

        EntityModel<Pedido> entityModelPedido = pedidoModelAssembler.toModel(pedido);

        return ResponseEntity.ok(entityModelPedido);
    }

    // Excluir a Pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedidoPorId(@PathVariable("id") Integer id) {
        pedidoService.excluirPedido(id);

        return ResponseEntity.noContent().build();
    }

    // Atualizar os Produtos da Pedido
    // @PatchMapping("/{id}/produtos")
    // public ResponseEntity<EntityModel<Pedido>>
    // atualizarProdutosDoPedido(@PathVariable("id") Integer idPedido,
    // List<Produto> produtos) {
    // Pedido pedido = pedidoService.atualizarProdutosDoPedido(idPedido, produtos);

    // EntityModel<Pedido> entityModelPedido = pedidoModelAssembler.toModel(pedido);

    // return ResponseEntity.ok(entityModelPedido);
    // }
}
