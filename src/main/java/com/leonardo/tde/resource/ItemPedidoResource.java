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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.tde.domain.ItemPedido;
import com.leonardo.tde.domain.ItemPedidoPK;
import com.leonardo.tde.domain.model_assembler.ItemPedidoModelAssembler;
import com.leonardo.tde.service.ItemPedidoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// API do ItemPedido - Versão 1
@RestController
@RequestMapping("/api/v1/itempedido")
public class ItemPedidoResource {

    // Objetos
    // Serviço do ItemPedido
    @Autowired
    private ItemPedidoService itemPedidoService;

    // Montador de Modelo do ItemPedido
    // @Autowired
    private ItemPedidoModelAssembler itemPedidoModelAssembler = new ItemPedidoModelAssembler();

    // API's
    // Obters Todos os ItemPedidos
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ItemPedido>>> obterTodosItemPedidos() {
        List<ItemPedido> itemPedidos = itemPedidoService.todosItemPedidos();

        if (!itemPedidos.isEmpty()) {
            List<EntityModel<ItemPedido>> listaEntityModelItemPedidos = new ArrayList<EntityModel<ItemPedido>>();

            listaEntityModelItemPedidos.addAll(itemPedidos.stream().map(itemPedidoModelAssembler::toModel).toList());

            CollectionModel<EntityModel<ItemPedido>> collectionModel;
            collectionModel = CollectionModel.of(listaEntityModelItemPedidos);

            return ResponseEntity.ok(collectionModel);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obter ItemPedido Por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ItemPedido>> obterItemPedidoPorId(@RequestAttribute("id") ItemPedidoPK id) {
        ItemPedido itemPedido = itemPedidoService.itemPedidoPorId(id);

        EntityModel<ItemPedido> entityModelItemPedido = itemPedidoModelAssembler.toModel(itemPedido);

        return ResponseEntity.ok(entityModelItemPedido);
    }

    // Salvar a ItemPedido
    @PostMapping
    public ResponseEntity<EntityModel<ItemPedido>> salvarItemPedido(@RequestBody @Valid ItemPedido itemPedido,
            HttpServletRequest request) {
        itemPedidoService.salvarItemPedido(itemPedido);

        URI uri = linkTo(methodOn(ItemPedidoResource.class).obterItemPedidoPorId(itemPedido.getItemPedidoPK())).withSelfRel().toUri();
        EntityModel<ItemPedido> entityModelItemPedido = itemPedidoModelAssembler.toModel(itemPedido);

        return ResponseEntity.created(uri).body(entityModelItemPedido);
    }

    // Atualizar a ItemPedido
    @PutMapping
    public ResponseEntity<EntityModel<ItemPedido>> atualizarItemPedido(@RequestBody ItemPedido itemPedido) {
        itemPedidoService.itemPedidoPorId(itemPedido.getItemPedidoPK());
        itemPedidoService.atualizarItemPedido(itemPedido);

        EntityModel<ItemPedido> entityModelItemPedido = itemPedidoModelAssembler.toModel(itemPedido);

        return ResponseEntity.ok(entityModelItemPedido);
    }

    // Excluir a ItemPedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemPedidoPorId(@RequestParam("id") ItemPedidoPK id) {
        itemPedidoService.excluirItemPedido(id);

        return ResponseEntity.noContent().build();
    }

    // Atualizar os Cidades da ItemPedido
    // @PatchMapping("/{id}/cidades")
    // public ResponseEntity<EntityModel<ItemPedido>> atualizarCidadesDoItemPedido(@PathVariable("id") Integer idItemPedido,
    //         List<Cidade> cidades) {
    //     ItemPedido itemPedido = itemPedidoService.atualizarCidadesDoItemPedido(idItemPedido, cidades);

    //     EntityModel<ItemPedido> entityModelItemPedido = itemPedidoModelAssembler.toModel(itemPedido);

    //     return ResponseEntity.ok(entityModelItemPedido);
    // }
}
