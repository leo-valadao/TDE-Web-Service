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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.tde.domain.Cliente;
import com.leonardo.tde.domain.Endereco;
import com.leonardo.tde.domain.model_assembler.ClienteModelAssembler;
import com.leonardo.tde.service.ClienteService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// API do Cliente - Versão 1
@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteResource {

    // Objetos
    // Serviço do Cliente
    @Autowired
    private ClienteService clienteService;

    // Montador de Modelo do Cliente
    // @Autowired
    private ClienteModelAssembler clienteModelAssembler;

    // API's
    // Obter Todos os Clientes
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> obterTodosClientes() {
        List<Cliente> clientes = clienteService.todosClientes();

        if (!clientes.isEmpty()) {
            List<EntityModel<Cliente>> listaEntityModelClientes = new ArrayList<EntityModel<Cliente>>();

            listaEntityModelClientes.addAll(clientes.stream().map(clienteModelAssembler::toModel).toList());

            CollectionModel<EntityModel<Cliente>> collectionModel;
            collectionModel = CollectionModel.of(listaEntityModelClientes);

            return ResponseEntity.ok(collectionModel);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Obter Cliente Por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> obterClientePorId(@PathVariable("id") Integer id) {
        Cliente cliente = clienteService.clientePorId(id);

        EntityModel<Cliente> entityModelCliente = clienteModelAssembler.toModel(cliente);

        return ResponseEntity.ok(entityModelCliente);
    }

    // Salvar o Cliente
    @PostMapping
    public ResponseEntity<EntityModel<Cliente>> salvarCliente(@RequestBody @Valid Cliente cliente,
            HttpServletRequest request) {
        clienteService.salvarCliente(cliente);

        URI uri = linkTo(methodOn(ClienteResource.class).obterClientePorId(cliente.getId())).withSelfRel().toUri();
        EntityModel<Cliente> entityModelCliente = clienteModelAssembler.toModel(cliente);

        return ResponseEntity.created(uri).body(entityModelCliente);
    }

    // Atualizar o Cliente
    @PutMapping
    public ResponseEntity<EntityModel<Cliente>> atualizarCliente(@RequestBody Cliente cliente) {
        clienteService.clientePorId(cliente.getId());
        clienteService.atualizarCliente(cliente);

        EntityModel<Cliente> entityModelCliente = clienteModelAssembler.toModel(cliente);

        return ResponseEntity.ok(entityModelCliente);
    }

    // Excluir o Cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClientePorId(@PathVariable("id") Integer id) {
        clienteService.excluirCliente(id);

        return ResponseEntity.noContent().build();
    }

    // Atualizar o Endereço do Cliente
    @PatchMapping("/{id}/enderecos")
    public ResponseEntity<EntityModel<Cliente>> atualizarEnderecosDoCliente(@PathVariable("id") Integer idCliente,
            List<Endereco> enderecos) {
        Cliente cliente = clienteService.atualizarEnderecosDoCliente(idCliente, enderecos);

        EntityModel<Cliente> entityModelCliente = clienteModelAssembler.toModel(cliente);

        return ResponseEntity.ok(entityModelCliente);
    }
}
